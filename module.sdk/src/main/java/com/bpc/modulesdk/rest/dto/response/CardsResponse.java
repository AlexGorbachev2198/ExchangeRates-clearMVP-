package com.bpc.modulesdk.rest.dto.response;

import com.bpc.modulesdk.rest.dto.pojo.Card;

import java.util.Collections;
import java.util.List;

/**
 * Created by Samoylov on 20.01.2017.
 */

public class CardsResponse extends MainResponse {

    public List<Card> cards = Collections.emptyList();

}