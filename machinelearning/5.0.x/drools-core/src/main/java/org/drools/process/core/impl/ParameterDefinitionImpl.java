package org.drools.process.core.impl;

import java.io.Serializable;

import org.drools.process.core.ParameterDefinition;
import org.drools.process.core.datatype.DataType;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class ParameterDefinitionImpl implements ParameterDefinition, Serializable {
   
    private static final long serialVersionUID = 400L;
    
    public ParameterDefinitionImpl() {
    }
    
    public ParameterDefinitionImpl(String name, DataType type) {