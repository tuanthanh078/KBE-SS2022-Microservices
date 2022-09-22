import React, { Component } from "react";

const ENDPOINT_PRODUCTS = "/products";
const ENDPOINT_COMPONENTS = "/components";

class Main extends Component{
    constructor(props){
        super(props);

        this.state = {elements: []};

        this.onSubmit = this.onSubmit.bind(this);
        this.updateProducts = this.updateProducts.bind(this);
        this.setProducts = this.setProducts.bind(this);
        this.updateComponents = this.updateComponents.bind(this);
        this.setComponents = this.setComponents.bind(this);

        this.displayed = "products";
        this.updateProducts();
    }

    onSubmit(category){
        if(category === "products" && this.displayed !== "products"){
            this.updateProducts();
            this.displayed = category;
        }else if(category === "components" && this.displayed !== "components"){
            this.updateComponents();
            this.displayed = category;
        }
    }

    updateProducts(){
        let productRequest = new XMLHttpRequest();
        productRequest.open("GET", this.props.url+ENDPOINT_PRODUCTS);
        productRequest.onreadystatechange = (e) => {
            if(productRequest.readyState === 4 && productRequest.status === 200){
                this.setProducts(productRequest.response);
            }
        }
        productRequest.send();
    }

    setProducts(productsResponse){
        let elements = JSON.parse(productsResponse);
        let listElements = elements.map(
            product =>
                <li key={product.id} className='product'>
                    <div className='productList'>
                        <label id='listProcessor' className='listProduct'>{product.processor.name}</label>
                        <label id='listGraphics' className='listProduct'>{product.graphics.name}</label>
                        <label id='listStorage' className='listProduct'>{product.storage.name}</label>
                    </div>
                </li>
        );
        this.setState({elements: listElements});
    }

    updateComponents(){
        let componentRequest = new XMLHttpRequest();
        componentRequest.open("GET", this.props.url+ENDPOINT_COMPONENTS);
        componentRequest.onreadystatechange = (e) => {
            if(componentRequest.readyState === 4 && componentRequest.status === 200){
                this.setComponents(componentRequest.response);
            }
        }
        componentRequest.send();
    }

    setComponents(componentsResponse){
        let elements = JSON.parse(componentsResponse);
        let listElements = elements.map(
            component => <li key={component.id}>{component.brand + " - " + component.name}</li>
        );
        this.setState({elements: listElements});
    }

    render(){
        return(
            <div name='main'>
                <NavigationBar onSubmit={this.onSubmit}/>
                <ElementList elements={this.state.elements}/>
                {(this.state.element !== undefined) ?
                    <Details element={this.state.element}/> :
                    null
                }
            </div>
        );
    }
}

class NavigationBar extends Component{
    constructor(props){
        super(props);
        this.state = {value: "products"};

        this.handleChange = this.handleChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    handleChange(event){
        this.setState({value: event.target.value});
    }

    onSubmit(){
        if(typeof this.props.onSubmit === "function"){
            this.props.onSubmit(this.state.value);
        }
    }

    render(){
        return(
            <div className='bar'>
                <select value={this.state.value} onChange={this.handleChange}>
                    <option value="products">Products</option>
                    <option value="components">Components</option>
                </select>
                <input type="text" placeholder="Search"/>
                <button onClick={this.onSubmit}>Show</button>
            </div>
        );
    }
}

class ElementList extends Component{

    render(){
        return(
            <ul>
                {this.props.elements}
            </ul>
        );
    }
}

class Details extends Component{
    render(){
        return(
            <div className='details'>

            </div>
        );
    }
}

export default Main;