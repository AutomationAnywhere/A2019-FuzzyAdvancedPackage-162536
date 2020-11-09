package com.automationanywhere.botcommand.sk;

import java.util.Iterator;
import java.util.List;

import com.automationanywhere.botcommand.data.Value;

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



import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.sk.tokenzier.FuzzyRatio;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;

import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.ConditionTest;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Token Ratio Match", name = "TokenSortRatioMatch",
        description = "Fuzzy Match Token Ratio",
        node_label = "Token Ratio",  icon = "pkg.svg",
        return_type=DataType.NUMBER, return_label="Ratio", return_required=true)

public class TokenSortRatio  {
	
	   
	@Execute
    public NumberValue ratio(@Idx(index = "1", type = AttributeType.LIST)  @Pkg(label = "Token List 1" , default_value_type = DataType.LIST) @NotEmpty List<Value> list1,
    						@Idx(index = "2", type = AttributeType.LIST)  @Pkg(label = "Token List 2"  , default_value_type = DataType.LIST) @NotEmpty List<Value> list2)
     {
    	   
		   String str1 = "";
		   String str2 = "";
		   for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			Value value = (Value) iterator.next();
			str1 = str1+" "+(String)value.get();
		   }
		   for (Iterator iterator = list2.iterator(); iterator.hasNext();) {
			Value value = (Value) iterator.next();
			str2 = str2+" "+(String)value.get();
		   }
		   str1 = str1.trim();
		   str2 = str2.trim();
		   
		   Integer ratio = FuzzyRatio.TokenSortRatioMatch(str1, str2);
    	   
		   return new NumberValue(ratio);

    	}   
}
	
