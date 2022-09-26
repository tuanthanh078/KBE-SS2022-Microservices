import React, { Component } from "react";

import ComponentDetails from "./ComponentDetails";

class ProductDetails extends Component{
    constructor(props){
        super(props);

        this.state = {value: "processor", component: this.props.product.processor};

        this.handleChange = this.handleChange.bind(this);
        this.componentDidUpdate = this.componentDidUpdate.bind(this);
        this.getComponent = this.getComponent.bind(this);
    }

    componentDidUpdate(prevProps){
        if(prevProps.product !== this.props.product){
            let component = this.getComponent(this.state.value);
            this.setState({component: component});
        }
    }

    handleChange(e){
        let component = this.getComponent(e.target.value);
        this.setState({value: event.target.value, component: component});
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
        return(
            <div className='details' id='productDetails'>
                <div className='productHeadline'>
                    <label id='productHeadline'>Product</label>
                    <label id='productHeadlineId'>{" (" + this.props.product.id + ")"}</label>
                    <select value={this.state.value} onChange={this.handleChange} id='productHeadlineSelect'>
                        <option value="processor">Processor</option>
                        <option value="graphics">Graphics</option>
                        <option value="storage">Storage</option>
                    </select>
                </div>
                <ComponentDetails component={this.state.component} currency={this.props.currency} url={this.props.url}/>
            </div>
        );
    }
}

export default ProductDetails;