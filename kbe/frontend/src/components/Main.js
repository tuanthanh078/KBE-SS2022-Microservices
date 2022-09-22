import React, { Component } from "react";

const ENDPOINT_PRODUCTS = "/products";
const ENDPOINT_COMPONENTS = "/components";

class Main extends Component{
    constructor(props){
        super(props);

        this.state = {elements: [], component: null, product: null};

        this.onSubmit = this.onSubmit.bind(this);
        this.updateProducts = this.updateProducts.bind(this);
        this.onSelectProduct = this.onSelectProduct.bind(this);
        this.updateComponents = this.updateComponents.bind(this);
        this.onSelectComponent = this.onSelectComponent.bind(this);

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
                this.setState({elements: JSON.parse(productRequest.response), component: null});
            }
        }
        productRequest.send();
    }

    onSelectProduct(product){
        this.setState({product: product});
    }

    updateComponents(){
        let componentRequest = new XMLHttpRequest();
        componentRequest.open("GET", this.props.url+ENDPOINT_COMPONENTS);
        componentRequest.onreadystatechange = (e) => {
            if(componentRequest.readyState === 4 && componentRequest.status === 200){
                this.setState({elements: JSON.parse(componentRequest.response), product: null});
            }
        }
        componentRequest.send();
    }

    onSelectComponent(component){
        this.setState({component: component});
    }

    render(){
        return(
            <div name='main' className='main'>
                <NavigationBar onSubmit={this.onSubmit}/>
                <ElementList elements={this.state.elements} category={this.displayed} onSelectComponent={this.onSelectComponent} onSelectProduct={this.onSelectProduct}/>
                {(this.state.component !== null) ?
                    <ComponentDetails component={this.state.component}/> :
                    null
                }
                {(this.state.product !== null) ?
                    <ProductDetails product={this.state.product}/> :
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
                <ComponentDetails component={this.state.component}/>
            </div>
        );
    }
}

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

class KeyValueDisplay extends Component{
    render(){
        return(
            <div className='keyValueDisplay'>
                <label className='key'>{this.props.keyName + ": "}</label>
                <label className='value'>{this.props.value}</label>
            </div>
        );
    }
}

export default Main;