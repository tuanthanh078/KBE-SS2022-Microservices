import React, { Component } from "react";

import KeyValueDisplay from "../KeyValueDisplay.js";

class ComponentDetails extends Component{
    render(){
        return(
            <div className='details' id='componentDetails'>
                <h3>{this.props.component.brand + " - " + this.props.component.name}</h3>
                <KeyValueDisplay keyName='Id' value={this.props.component.id} id='idDetail'/>
                <KeyValueDisplay keyName='Type' value={this.props.component.type}/>
                <KeyValueDisplay keyName='Price' value={this.props.component.price.toFixed(2)}/>
                <KeyValueDisplay keyName='Deliverable' value={this.props.component.deliverable ? "Yes" : "No"}/>
                <KeyValueDisplay keyName='Location' value={this.props.component.location}/>
                <KeyValueDisplay keyName='Dimensions' value={this.props.component.width + "mm, " + this.props.component.length + "mm"}/>
                <KeyValueDisplay keyName='Power' value={this.props.component.power}/>
            </div>
        );
    }
}

export default ComponentDetails;