import React, { Component } from "react";

import KeyValueDisplay from "../KeyValueDisplay.js";
import sendHtmlRequest from "./Main.js";

const ENDPOINT_CURRENCY = "/currency";

function getCurrencySymbol(currency){
    switch(currency){
        case "EUR":
            return '€';
        case "GBP":
            return '£';
        case "JPY":
            return '¥';
        case "CHF":
            return 'CHF';
        default:
            return '$';
    }
}

function requestPrice(url, onReceive, onFail, value, priorCurrency, currency){
    let requestParams =
        "?amount="+value+
        "&currentCurrency="+priorCurrency+
        "&newCurrency="+currency;
    sendHtmlRequest("GET", url + ENDPOINT_CURRENCY + requestParams, onReceive, onFail);
}

class ComponentDetails extends Component{
    constructor(props){
        super(props);

        this.state = {price: null};
    }

    onReceivePrice(response){
        console.log(response);
    }

    onFailPrice(){
        this.setState({price: "ERR"});
    }

    render(){
        return(
            <div className='details' id='componentDetails'>
                <h3>{this.props.component.brand + " - " + this.props.component.name}</h3>
                <KeyValueDisplay keyName='Id' value={this.props.component.id} id='idDetail'/>
                <KeyValueDisplay keyName='Type' value={this.props.component.type}/>
                <KeyValueDisplay keyName='Price' value={this.props.component.price.toFixed(2) + " " + getCurrencySymbol(this.props.currency)}/>
                <KeyValueDisplay keyName='Deliverable' value={this.props.component.deliverable ? "Yes" : "No"}/>
                <KeyValueDisplay keyName='Location' value={this.props.component.location + " (" + this.props.component.osmLat.toFixed(3) + ", " + this.props.component.osmLon.toFixed(3) + ")"}/>
                <KeyValueDisplay keyName='Dimensions' value={this.props.component.width + "mm, " + this.props.component.length + "mm"}/>
                <KeyValueDisplay keyName='Power' value={this.props.component.power}/>
                <KeyValueDisplay keyName='Manufactured' value={this.props.component.date.substring(0, 10)}/>
            </div>
        );
    }
}

export default ComponentDetails;