package com.automationanywhere.botcommand.sk.tokenzier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;


import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 * A port of the BERT WordpieceTokenizer in the <a href="https://github.com/google-research/bert">BERT GitHub Repository</a>.
 *
 * The WordpieceTokenizer processes tokens from the {@link com.robrua.nlp.bert.BasicTokenizer} into sub-tokens - parts of words that compose BERT's vocabulary.
 * These tokens can then be converted into the inputIds that the BERT model accepts.
 *
 * @author Rob Rua (https://github.com/robrua)
 * @version 1.0.3
 * @since 1.0.3
 *
 * @see <a href="https://github.com/google-research/bert/blob/master/tokenization.py">The Python tokenization code this is ported from</a>
 * 
 */




public class WordpieceTokenizer extends Tokenizer {
    private static final int DEFAULT_MAX_CHARACTERS_PER_WORD = 200;
    private static final String DEFAULT_UNKNOWN_TOKEN = "[UNK]";

    private final int maxCharactersPerWord;
    private final String unknownToken;
    private final List<Value> vocabulary;
    private boolean fuzzy = false;
    private boolean doNumeric = false;
    private Integer confidence = 90; 

	 private static final Logger logger = LogManager.getLogger(WordpieceTokenizer.class);
    
    
    /**
     * Creates a BERT {@link com.robrua.nlp.bert.WordpieceTokenizer}
     *
     * @param vocabulary
     *        a mapping from sub-tokens in the BERT vocabulary to their inputIds
     * @since 1.0.3
     */
    public WordpieceTokenizer(final List<Value> vocabulary) {
        this.vocabulary = vocabulary;
        unknownToken = DEFAULT_UNKNOWN_TOKEN;
        maxCharactersPerWord = DEFAULT_MAX_CHARACTERS_PER_WORD;
    }
    
    public WordpieceTokenizer(final List<Value> vocabulary,boolean doFuzzy) {
        this.vocabulary = vocabulary;
        unknownToken = DEFAULT_UNKNOWN_TOKEN;
        maxCharactersPerWord = DEFAULT_MAX_CHARACTERS_PER_WORD;
        this.fuzzy = doFuzzy;
        this.doNumeric = false;
    }

    
    public WordpieceTokenizer(final List<Value> vocabulary,boolean doFuzzy, boolean doNumeric, Integer confidence) {
        this.vocabulary = vocabulary;
        unknownToken = DEFAULT_UNKNOWN_TOKEN;
        maxCharactersPerWord = DEFAULT_MAX_CHARACTERS_PER_WORD;
        this.fuzzy = doFuzzy;
        this.confidence = confidence;
        this.doNumeric = doNumeric;
        
    }



    private Stream<String> splitToken(final String token) {
        final char[] characters = token.toCharArray();
        if(characters.length > maxCharactersPerWord) {
            return Stream.of(unknownToken);
        }

        final Stream.Builder<String> subtokens = Stream.builder();
        int start = 0;
        int end;

        while(start < characters.length) {
            end = characters.length;
        	logger.info("Outerloop "+end);
            boolean found = false;
            while(start < end) {
            	final String substring = (start > 0 ? "##" : "") + String.valueOf(characters, start, end - start);
                for (int i = 0; i < vocabulary.size(); i++) {
					String vocabl = vocabulary.get(i).get().toString();
	                if (fuzzy)
	                {
	                	if (isNumeric(substring))
	                	{   
	                	  if (doNumeric) {
	                         subtokens.accept(substring);
	                         start = end;
	                         found = true;
	                         break;
	                	   }
	                    }
	                	else {
	                		if (FuzzySearch.ratio(vocabl.toLowerCase(),substring.toLowerCase()) >= confidence) {
	                		   subtokens.accept(vocabl);
	                		   start = end;
	               			   found = true;
	               			   break;
	               		     }
	                	 }
	                }
	                else
	                {
	                	if (isNumeric(substring))
	                	{   
	                	  if (doNumeric) {
	                         subtokens.accept(substring);
	                         start = end;
	                         found = true;
	                         break;
	                	   }
	                	}
	                	else {
	                		if (vocabl.toLowerCase().equals(substring.toLowerCase())) {
	                		subtokens.accept(substring);
	                		start = end;
	                		found = true;
	                		break;
	                		}
	                	}
	                }
                }	
                
                if(!found) {
                	end--;
                }
            }	
            if(!found) {
               // subtokens.accept(unknownToken);
                break;
            }
            start = end;
        }
        return subtokens.build();
    }

    @Override
    public String[] tokenize(final String sequence) {
        return whitespaceTokenize(sequence)
            .flatMap(this::splitToken)
            .toArray(String[]::new);
    }

    @Override
    public String[][] tokenize(final String... sequences) {
        return Arrays.stream(sequences)
            .map((final String sequence) -> whitespaceTokenize(sequence).toArray(String[]::new))
            .map((final String[] tokens) -> Arrays.stream(tokens)
                .flatMap(this::splitToken)
                .toArray(String[]::new))
            .toArray(String[][]::new);
    }
    
    
    public static boolean isNumeric(String str)
    {
    	
    	str = str.replaceAll("\\.","").replaceAll(",","");
    	if (str.length() == 0) return false;
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    public static int listSizenoNumeric( List<Value> list) {
    	int size =0;
    	for (Value value : list) {
    		if (!isNumeric(value.toString())) {
    			size++;
    		}
		}
    	return size;
    	
    }
 
    
}
