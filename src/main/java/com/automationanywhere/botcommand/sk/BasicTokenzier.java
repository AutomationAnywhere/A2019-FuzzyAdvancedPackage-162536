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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;

import com.automationanywhere.botcommand.sk.tokenzier.BasicTokenizer;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;

import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;

/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Basic Tokenizer", name = "basictokenizer",
        description = "Get Tokens from a String",
        node_label = "Get Tokens from a String", icon = "pkg.svg",
        return_type=DataType.LIST, return_sub_type =DataType.STRING, return_label="Tokens", return_required=true)
public class BasicTokenzier {
	
	private static final Logger logger = LogManager.getLogger(BasicTokenzier.class);
	   
	@Execute
    public ListValue<String> action(@Idx(index = "1", type = TEXT)  @Pkg(label = "Text" , default_value_type = STRING) @NotEmpty String text ) throws Exception
     {

		BasicTokenizer basictokenizer = new BasicTokenizer(false);
		String[] tokens = basictokenizer.tokenize(text);
		List<Value> values = new ArrayList<Value>();
		for (int i = 0; i < tokens.length; i++) {
			values.add(new StringValue(tokens[i]));
			
		}
		ListValue<String> value = new ListValue<String>();
		value.set(values);
		return value;
     
     }
		
	
}