import React, { Component } from "react";
import axios from "axios";

class Temp extends Component {
  constructor(props) {
    super(props);
    this.props = props;
    this.SERVER_URL = 'http://localhost:8085';
    this.GET_PRODUCTS = '/products';
    this.emptyProduct = {id: "", name: "", selectedHardwares: {hardwareId: "", selectedAmount: ""}};
    this.state = {
      getProductByIdResponse: this.emptyProduct,
      getProductsResponse: []
    };
    this.getProducts = this.getProducts.bind(this);
    this.getProductByID = this.getProductByID.bind(this);
  }

  async componentDidMount() {
    await this.getProducts();
    // await this.getProductByID(1);
  }

  async getProducts() {
    await axios
            .get(this.SERVER_URL + this.GET_PRODUCTS)
            .then(res => this.setState({ getProductsResponse: res.data }))
            .catch(err => {
              console.error(
                'There was an error at getProducts!', err
              );
            }); 
  }

  async getProductByID(id) {
    await axios
            .get(this.SERVER_URL + this.GET_PRODUCTS + "/" + id)
            .then(res => this.setState({ getProductByIdResponse: res.data }))
            .catch(err => {
              console.error(
                'There was an error at getProductByID!', err
              );
            }); 
  }

  render() {
    return (
      <div>
        getProductsResponse:
        <ul>
          {this.state.getProductsResponse.map((x) => (
            <li>{JSON.stringify(x)}</li>
          ))}
        </ul>
        getProductByIdResponse:
        {JSON.stringify(this.state.getProductByIdResponse)}
      </div>
    );
  }
}

export default Temp;