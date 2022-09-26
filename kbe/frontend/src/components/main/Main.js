import React, { Component } from "react";

import NavigationBar from "./NavigationBar";
import ElementList from "./ElementList";
import ComponentDetails from "./ComponentDetails";
import ProductDetails from "./ProductDetails";
import ProductCreation from "./ProductCreation";

const ENDPOINT_PRODUCTS = "/products";
const ENDPOINT_COMPONENTS = "/components";
const ENDPOINT_PRODUCTS_CREATE = "/products/custom";

const DISPLAY_PRODUCTS = "products";
const DISPLAY_COMPONENTS = "components";
const DISPLAY_CREATE_PRODUCT = "create";
const DISPLAY_ERROR = "error";

const MESSAGE_CREATE_FAILED = "Could not create product.";

function requestComponents(url, onReceive, onFail){
    sendHtmlRequest("GET", url+ENDPOINT_COMPONENTS, onReceive, onFail);
}
function requestProducts(url, onReceive, onFail){
    sendHtmlRequest("GET", url+ENDPOINT_PRODUCTS, onReceive, onFail);
}
function requestCreateProduct(url, product, onReceive, onFail){
    sendHtmlRequest("POST", url+ENDPOINT_PRODUCTS_CREATE, onReceive, onFail, product);
}
function sendHtmlRequest(method, url, onReceive, onFail, body){
    let request = new XMLHttpRequest();
    request.open(method, url);
    request.onreadystatechange = (e) => {
        if(request.readyState === 4){
            if(request.status >= 200 && request.status < 300){
                onReceive(request.response);
            }else{
                onFail({status: request.status, response: request.response});
            }
        }
    }
    if(body !== undefined)
        request.send(JSON.stringify(body));
    else
        request.send();
}

class Main extends Component{
    constructor(props){
        super(props);

        this.state = {display: DISPLAY_PRODUCTS, products: [], components: [], component: null, product: null};

        this.onSubmit = this.onSubmit.bind(this);
        this.displayProducts = this.displayProducts.bind(this);
        this.updateProducts = this.updateProducts.bind(this);
        this.onSelectProduct = this.onSelectProduct.bind(this);
        this.displayComponents = this.displayComponents.bind(this);
        this.updateComponents = this.updateComponents.bind(this);
        this.onSelectComponent = this.onSelectComponent.bind(this);
        this.onUpdateFail = this.onUpdateFail.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onSubmitProduct = this.onSubmitProduct.bind(this);
        this.onCreated = this.onCreated.bind(this);
        this.onCreateFail = this.onCreateFail.bind(this);
        this.getContent = this.getContent.bind(this);
    }

    componentDidMount(){
        requestProducts(this.props.url, this.updateProducts, this.onUpdateFail);
        requestComponents(this.props.url, this.updateComponents, this.onUpdateFail);

        this.displayProducts();
    }

    onSubmit(options){
        if(options.type === "products" && this.state.display !== DISPLAY_PRODUCTS){
            this.displayProducts();
        }else if(options.type === "components" && this.state.display !== DISPLAY_COMPONENTS){
            this.displayComponents();
        }
    }

    displayProducts(){
        this.setState({component: null, display: DISPLAY_PRODUCTS});
    }

    updateProducts(response){
        this.setState({products: JSON.parse(response)});
    }

    onSelectProduct(product){
        this.setState({product: product});
    }

    displayComponents(){
        this.setState({product: null, display:DISPLAY_COMPONENTS});
    }

    updateComponents(response){
        this.setState({components: JSON.parse(response)});
    }

    onSelectComponent(component){
        this.setState({component: component});
    }

    onUpdateFail(){
        this.setState({display: DISPLAY_ERROR});
    }

    onCreate(){
        this.setState({display: DISPLAY_CREATE_PRODUCT});
    }

    onSubmitProduct(product){
        this.setState({createFailed: false});
        requestCreateProduct(this.props.url, product, this.onCreated, this.onCreateFail);
    }

    onCreated(response){
        console.log("PRODUCT created");
    }

    onCreateFail(){
        this.setState({createFailed: true});
    }

    getContent(){
        let content;
        let elements = this.state.components;

        switch(this.state.display){
            case DISPLAY_PRODUCTS:
                elements = this.state.products;
                // fallthrough
            case DISPLAY_COMPONENTS:
                content =
                    <div>
                        <ElementList elements={elements} category={this.state.display} onSelectComponent={this.onSelectComponent} onSelectProduct={this.onSelectProduct}/>
                        {(this.state.component !== null) ?
                            <ComponentDetails component={this.state.component}/> :
                            null
                        }
                        {(this.state.product !== null) ?
                            <ProductDetails product={this.state.product}/> :
                            null
                        }
                    </div>
                break;

            case DISPLAY_ERROR:
                content =
                    <label className="error">An error occurred while loading products and components.</label>
                break;

            case DISPLAY_CREATE_PRODUCT:
                content =
                    <ProductCreation components={this.state.components} onSubmit={this.onSubmitProduct} error={this.state.createFailed ? MESSAGE_CREATE_FAILED : undefined}/>
                break;

            default:
                content = null;
        }

        return content;
    }

    render(){
        return(
            <div name='main' className='main'>
                <NavigationBar onSubmit={this.onSubmit} onCreate={this.onCreate}/>
                {this.getContent()}
            </div>
        );
    }
}

export default Main;