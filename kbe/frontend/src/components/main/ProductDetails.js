import React, { Component } from "react";

import { price, currencySymbol } from "../Price.js"
import ComponentDetails from "./ComponentDetails";
import KeyValueDisplay from "../KeyValueDisplay.js";

const CURRENCY_US_DOLLAR = "USD";

class ProductDetails extends Component{
    constructor(props){
        super(props);

        this.state = {value: "processor", component: this.props.product.processor, price: this.props.product.price};

        this.handleChange = this.handleChange.bind(this);
        this.sendPriceRequest = this.sendPriceRequest.bind(this);
        this.onReceivePrice = this.onReceivePrice.bind(this);
        this.onFailPrice = this.onFailPrice.bind(this);
        this.getComponent = this.getComponent.bind(this);

        this.sendPriceRequest();
    }

    componentDidUpdate(prevProps){
        if(this.props.currency === CURRENCY_US_DOLLAR && this.state.price !== this.props.product.price)
            this.setState({price: this.props.product.price});
        else if(this.props.currency !== prevProps.currency || this.props.product.price !== prevProps.product.price)
            this.sendPriceRequest();

        if(prevProps.product !== this.props.product){
            let component = this.getComponent(this.state.value);
            this.setState({component: component});
        }
    }

    handleChange(e){
        let component = this.getComponent(e.target.value);
        this.setState({value: event.target.value, component: component});
    }

    sendPriceRequest(){
        price(this.props.url, this.onReceivePrice, this.onFailPrice, this.props.product.price, this.props.currency);
    }

    onReceivePrice(response){
        this.setState({price: response});
    }

    onFailPrice(){
        this.setState({price: "ERR"});
    }

    getComponent(type){
        let component;
        switch(type) {
            case "processor":
                component = this.props.product.processor;
                break;
            case "graphics":
                component = this.props.product.graphics;
                break;
            case "storage":
                component = this.props.product.storage;
                break;
            default:
        }
        return component;
    }

    render(){
        let price = this.state.price === null ? "-" : parseFloat(this.state.price).toFixed(2) + " " + currencySymbol(this.props.currency);
        return(
            <div className='details' id='productDetails'>
                <div className='productHeadline'>
                    <label id='productHeadline'>Product</label>
                    <select value={this.state.value} onChange={this.handleChange} id='productHeadlineSelect'>
                        <option value="processor">Processor</option>
                        <option value="graphics">Graphics</option>
                        <option value="storage">Storage</option>
                    </select>
                </div>
                <KeyValueDisplay keyName="Price" value={price}/>
                <ComponentDetails component={this.state.component} currency={this.props.currency} url={this.props.url}/>
            </div>
        );
    }
}

export default ProductDetails;