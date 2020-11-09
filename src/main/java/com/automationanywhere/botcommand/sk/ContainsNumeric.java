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





import java.util.Iterator;
import java.util.List;

import com.automationanywhere.botcommand.data.Value;
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
@CommandPkg(label = "Contains Numerical", name = "containsnumeric",
        description = "List contains numerical tokens",
        node_label = "Tokens {{list}} contain numerical tokens}", icon = "pkg.svg")
public class ContainsNumeric  {
	
	   
    @ConditionTest
    public boolean test(@Idx(index = "1", type = AttributeType.LIST)  @Pkg(label = "List 1" , default_value_type = DataType.LIST) @NotEmpty List<Value> list)
     {
    	   boolean containsnumeric = false;
    	   
    	   for (Iterator iterator = list.iterator(); iterator.hasNext();) {
    		  Value value = (Value) iterator.next();
    		  if (WordpieceTokenizer.isNumeric(value.toString())) {
    			containsnumeric = true;
    		  }
    	   }
    		    	   
    	   return containsnumeric;

    	}   
}
	
