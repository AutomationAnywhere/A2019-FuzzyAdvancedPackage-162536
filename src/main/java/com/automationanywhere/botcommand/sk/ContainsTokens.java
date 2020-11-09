package com.automationanywhere.botcommand.sk;

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


import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.AttributeType.VARIABLE;
import static com.automationanywhere.commandsdk.model.AttributeType.CHECKBOX;

import static com.automationanywhere.commandsdk.model.DataType.RECORD;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Iterator;
import java.util.List;
import static com.automationanywhere.commandsdk.model.DataType.BOOLEAN;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.record.Record;
import com.automationanywhere.botcommand.sk.tokenzier.WordpieceTokenizer;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;

import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.ConditionTest;
import com.automationanywhere.commandsdk.annotations.BotCommand.CommandType;

/**
 * @author Stefan Karsten
 *
 */

@BotCommand(commandType = CommandType.Condition)
@CommandPkg(label = "Contains Tokens", name = "containstokens",
        description = "List1 contains all tokens of List2, order is ignored",
        node_label = "Tokens {{list1}} contains {{list2}}", icon = "pkg.svg")
public class ContainsTokens  {
	
	   
    @ConditionTest
    public boolean test(@Idx(index = "1", type = AttributeType.LIST)  @Pkg(label = "List 1" , default_value_type = DataType.LIST) @NotEmpty List<Value> list1,
                        @Idx(index = "2", type = AttributeType.LIST)  @Pkg(label = "List 2"  , default_value_type = DataType.LIST) @NotEmpty List<Value> list2)
     {
    	   boolean contains = true;
    	   int counter = 0;
    	   boolean[] results = new boolean[list2.size()];
    	   
    	   int listsize1 = WordpieceTokenizer.listSizenoNumeric(list1);
    	   int listsize2 = WordpieceTokenizer.listSizenoNumeric(list2);
    	   
    	   if (listsize1 < listsize2)
    	   {
    		    contains = false;
    	   }
    	   else
    	   {
    	   
    		   for (Iterator iterator2 = list2.iterator(); iterator2.hasNext();) {
    			   Value value2 = (Value) iterator2.next();
    			   results[counter] = false;
    			   if (!WordpieceTokenizer.isNumeric(value2.toString())) {
    				   for (Iterator iterator1 = list1.iterator(); iterator1.hasNext();) {
    					   Value value1 = (Value) iterator1.next();
    					   if (!WordpieceTokenizer.isNumeric(value1.toString())) {
    						   if (value1.toString().toLowerCase().equals(value2.toString().toLowerCase())) {
    							   results[counter] = true;
    						   }
    					   }
    				   }
    			   }
    			   else
    			   {
    				   results[counter] = true;				
    			   }
    			   counter++;
    		   }
    	   
    	   
    		   for (int i = 0; i < results.length; i++) {
    			   if (results[i] == false)
    			   {
    				   contains = false;
    				   break;
    			   }
    		   }
    	   }
    	   
    	   return contains;

    	}   
}
	
