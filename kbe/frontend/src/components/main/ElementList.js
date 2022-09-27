import React, { Component } from "react";

class ElementList extends Component{
    onComponentClick(){
        if(typeof this.component.props.onSelectComponent === "function"){
            this.component.props.onSelectComponent(this.element);
        }
    }

    onProductClick(){
        if(typeof this.component.props.onSelectProduct === "function"){
            this.component.props.onSelectProduct(this.element);
        }
    }

    render(){
        let listElements;
        if(this.props.category === "products"){
            listElements = this.props.elements.map(
                product =>
                    <li key={product.id} onClick={this.onProductClick.bind({component: this, element: product})} className='product'>
                        <div className='productList'>
                            <label id='listProcessor' className='listProduct'>{product.processor.name}</label>
                            <label id='listGraphics' className='listProduct'>{product.graphics.name}</label>
                            <label id='listStorage' className='listProduct'>{product.storage.name}</label>
                        </div>
                    </li>
            );
        }else if(this.props.category === "components"){
            listElements = this.props.elements.map(
                component => <li key={component.id} onClick={this.onComponentClick.bind({component: this, element: component})}>{component.brand + " - " + component.name}</li>
            );
        }

        return(
            <div className='list'>
                <ul>
                    {listElements}
                </ul>
            </div>
        );
    }
}

export default ElementList;