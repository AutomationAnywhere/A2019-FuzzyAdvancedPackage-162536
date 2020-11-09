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
@CommandPkg(label = "Simple Ratio Match", name = "SimpleRatioMatch",
        description = "Fuzzy Match Simple Ratio",
        node_label = "Simple Ratio",  icon = "pkg.svg",
        return_type=DataType.NUMBER, return_label="Ratio", return_required=true)
public class SimpleRatio  {
	
	   
	@Execute
    public NumberValue ratio(@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "String 1" , default_value_type = DataType.STRING) @NotEmpty String str1,
                        @Idx(index = "2", type = AttributeType.TEXT)  @Pkg(label = "String 2"  , default_value_type = DataType.STRING) @NotEmpty String str2)
     {
    	   
    	   Integer ratio = FuzzyRatio.SimpleRatioMatch(str1, str2);
    	   return new NumberValue(ratio);

    	}   
}
	
