import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'
import TronWeb from 'tronweb'

// This provider is optional, you can just use a url for the nodes instead
const HttpProvider = TronWeb.providers.HttpProvider
const fullNode = new HttpProvider('https://api.trongrid.io') // Full node http endpoint
const solidityNode = new HttpProvider('https://api.trongrid.io') // Solidity node http endpoint
const eventServer = new HttpProvider('https://api.trongrid.io') // Contract events http endpoint

class App extends Component {
  constructor(props) {
    super(props)

    this.state = {
      tronWeb : null,
      balance : -1,
    }
  }

  async componentDidMount() {
    let tronWeb;
    let balance = -1;

    if (window.tronWeb && window.tronWeb.requestData) {
        tronWeb = new TronWeb(fullNode, solidityNode, eventServer, window.tronWeb.requestData())

        const dd = tronWeb.defaultAddress;

        console.log(dd.base58)

        // The majority of the function calls are asynchronus,
        // meaning that they cannot return the result instantly.
        // These methods therefore return a promise, which you can await.
        balance = await tronWeb.trx.getBalance(dd.base58)
        console.log({ balance })
    } else {
        alert('no tronWeb')
    }

    this.setState({
        tronWeb: tronWeb,
        balance: balance,
    })

    // // You can also bind a `then` and `catch` method.
    // tronWeb.trx.getBalance(address).then(balance => {
    //     console.log({balance});
    // }).catch(err => console.error(err));

    // // If you'd like to use a similar API to Web3, provide a callback function.
    // tronWeb.trx.getBalance(address, (err, balance) => {
    //     if (err)
    //         return console.error(err);

    //     console.log({balance});
    // });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a className="App-link" href="https://reactjs.org" target="_blank" rel="noopener noreferrer">
            balance : {this.state.balance}
          </a>
        </header>
      </div>
    )
  }
}

export default App
