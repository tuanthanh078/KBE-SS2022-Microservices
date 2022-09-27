import React, { Component } from "react";

import { price, currencySymbol } from "../Price.js"
import KeyValueDisplay from "../KeyValueDisplay.js";

const CURRENCY_US_DOLLAR = "USD";

class ComponentDetails extends Component{
    constructor(props){
        super(props);

        this.state = {price: this.props.component.price};

        this.sendPriceRequest = this.sendPriceRequest.bind(this);
        this.onReceivePrice = this.onReceivePrice.bind(this);
        this.onFailPrice = this.onFailPrice.bind(this);

        this.sendPriceRequest();
    }

    componentDidUpdate(prevProps){
        if(this.props.currency === CURRENCY_US_DOLLAR && this.state.price !== this.props.component.price)
            this.setState({price: this.props.component.price});
        else if(this.props.currency !== prevProps.currency || this.props.component.price !== prevProps.component.price)
            this.sendPriceRequest();
    }

    sendPriceRequest(){
        price(this.props.url, this.onReceivePrice, this.onFailPrice, this.props.component.price, this.props.currency);
    }

    onReceivePrice(response){
        this.setState({price: response});
    }

    onFailPrice(){
        this.setState({price: "ERR"});
    }

    render(){
        let price = this.state.price === null ? "-" : parseFloat(this.state.price).toFixed(2) + " " + currencySymbol(this.props.currency);
        return(
            <div className='details' id='componentDetails'>
                <h3>{this.props.component.brand + " - " + this.props.component.name}</h3>
                <KeyValueDisplay keyName='Id' value={this.props.component.id} id='idDetail'/>
                <KeyValueDisplay keyName='Type' value={this.props.component.type}/>
                <KeyValueDisplay keyName='Price' value={price}/>
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