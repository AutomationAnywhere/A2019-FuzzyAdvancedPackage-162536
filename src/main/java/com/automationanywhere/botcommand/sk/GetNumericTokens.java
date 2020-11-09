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
import java.util.Iterator;
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
@CommandPkg(label = "Get Numerical Tokens", name = "numericaltokens",
        description = "Get Numerical Tokens from the list",
        node_label = "Get Numerical Token", icon = "pkg.svg",
        return_type=DataType.LIST, return_sub_type =DataType.STRING, return_label="Numerical Tokens", return_required=true)
public class GetNumericTokens {
	   
	@Execute
    public ListValue<String> action(@Idx(index = "1", type = AttributeType.LIST)  @Pkg(label = "Tokens" , default_value_type = DataType.LIST) @NotEmpty List<Value> list) throws Exception
     {

		ListValue<String> returnvalue = new ListValue<String>();
		List<Value> values = new ArrayList<Value>();
 	
 	   for (Iterator iterator = list.iterator(); iterator.hasNext();) {
 		  Value value = (Value) iterator.next();
 		  if (WordpieceTokenizer.isNumeric(value.toString())) {
 			 values.add(value);
 		  }
 	   }
 	   
 	   returnvalue.set(values);
 	   return returnvalue; 
     
     }
		
	
}