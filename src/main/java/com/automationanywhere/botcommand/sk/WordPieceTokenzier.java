/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 * 
 */
package com.automationanywhere.botcommand.sk;



import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.ArrayList;
import java.util.List;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.sk.tokenzier.WordpieceTokenizer;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;

import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;

/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Detect Words", name = "wordpiecetokenizer",
        description = "Detects Words from a Vocabulary in a String",
        node_label = "Detect Words", icon = "pkg.svg",
        return_type=DataType.LIST, return_sub_type =DataType.STRING, return_label="Words", return_required=true)
public class WordPieceTokenzier {
	   
	@Execute
    public ListValue<String> action(@Idx(index = "1", type = TEXT)  @Pkg(label = "String" , default_value_type = STRING) @NotEmpty String text,
    								@Idx(index = "2", type = AttributeType.LIST)  @Pkg(label = "Vocabulary" , default_value_type = DataType.LIST) @NotEmpty List<Value> vocabulary,
    								@Idx(index = "3", type = AttributeType.BOOLEAN)  @Pkg(label = "Fuzzy" , default_value_type = DataType.BOOLEAN)  Boolean fuzzy,
    								@Idx(index = "4", type = AttributeType.BOOLEAN)  @Pkg(label = "Get Numbers" , default_value_type = DataType.BOOLEAN)  Boolean numeric,
    								@Idx(index = "5", type = AttributeType.NUMBER)  @Pkg(label = "Confidence (%)" , default_value_type = DataType.NUMBER)  Number confidence
    								) throws Exception
     {

		ListValue<String> value = new ListValue<String>();
		
		
	    boolean doFuzzy = (fuzzy != null) ? fuzzy : false;
	    Integer fuzzyConfidence = (confidence != null) ? confidence.intValue() : 90;
	    boolean doReplace = (numeric != null) ? numeric : false;

		
		WordpieceTokenizer wptokenizer = new WordpieceTokenizer(vocabulary,doFuzzy,doReplace,fuzzyConfidence);
				
		String[] tokens = wptokenizer.tokenize(text);
		
		List<Value> values = new ArrayList<Value>();
		for (int i = 0; i < tokens.length; i++) {
			values.add(new StringValue(tokens[i]));
		}
		value.set(values);
		return value; 
     
     }
		
	
}