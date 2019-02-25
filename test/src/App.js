import React, { Component } from 'react'
import logo from './logo.svg'
import './App.css'
import TronWeb from 'tronweb'

// This provider is optional, you can just use a url for the nodes instead
const HttpProvider = TronWeb.providers.HttpProvider
const fullNode = new HttpProvider('https://api.trongrid.io') // Full node http endpoint
const solidityNode = new HttpProvider('https://api.trongrid.io') // Solidity node http endpoint
const eventServer = new HttpProvider('https://api.trongrid.io') // Contract events http endpoint

const privateKey = 'da146374a75310b9666e834ee4ad0866d6f4035967bfc76217c5a495fff9f0d0'

const tronWeb = new TronWeb(fullNode, solidityNode, eventServer, privateKey)

class App extends Component {
  constructor(props) {
    super(props)

    this.state = {
      key: 'aaa'
    }
  }

  async componentDidMount() {
    if (window.DiceWeb) {
      //const result = window.DiceWeb.requestData()
      // this.setState({
      //   key: result
      // })
    } else {
      alert('no DiceWeb')
    }

    const address = tronWeb.address.fromPrivateKey(privateKey)

    // The majority of the function calls are asynchronus,
    // meaning that they cannot return the result instantly.
    // These methods therefore return a promise, which you can await.
    const balance = await tronWeb.trx.getBalance(address)
    console.log({ balance })

    this.setState({
      key: balance
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
            {this.state.key}
          </a>
        </header>
      </div>
    )
  }
}

export default App
