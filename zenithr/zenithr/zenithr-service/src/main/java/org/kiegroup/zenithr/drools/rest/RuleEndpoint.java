/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kiegroup.zenithr.drools.rest;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kiegroup.zenithr.drools.model.Output;
import org.kiegroup.zenithr.drools.model.RuleResult;
import org.kiegroup.zenithr.drools.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class RuleEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEndpoint.class);

    @Inject
    RuleService ruleService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(Map<String, Object> inputs) {
        try {
            if (inputs == null) {
                return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Json.createObjectBuilder().add("Error", "Missing input data").build())
                    .build();
            }
            Collection<Output> outputs = ruleService.process(Json.createObjectBuilder(inputs).build());
            return Response.ok(outputs.stream().map(RuleResult::new).collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOGGER.error("Unable to process request", e);
            return Response
                .serverError()
                .entity(Json.createObjectBuilder().add("Error", e.getLocalizedMessage()).build())
                .build();
        }
    }
}
