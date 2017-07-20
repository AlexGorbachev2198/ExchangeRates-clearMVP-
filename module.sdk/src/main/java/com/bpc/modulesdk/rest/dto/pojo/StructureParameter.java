package com.bpc.modulesdk.rest.dto.pojo;

import java.util.Collections;
import java.util.List;

public class StructureParameter extends CardOrAccountParameter {

        private List<CardOrAccountParameter> content= Collections.emptyList();

        public List<CardOrAccountParameter> getContent() {
            return content;
        }

        public void setContent(List<CardOrAccountParameter> content) {
            this.content = content;
        }

        public String getType() {
            return STRUCTURE;
        }


    }