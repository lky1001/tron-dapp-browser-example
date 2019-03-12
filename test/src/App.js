import React, { Component } from "react"
import logo from "./logo.svg"
import "./App.css"
import TronWeb from "tronweb"

// This provider is optional, you can just use a url for the nodes instead
const HttpProvider = TronWeb.providers.HttpProvider
const fullNode = new HttpProvider("https://api.trongrid.io") // Full node http endpoint
const solidityNode = new HttpProvider("https://api.trongrid.io") // Solidity node http endpoint
const eventServer = new HttpProvider("https://api.trongrid.io") // Contract events http endpoint

const FOUNDATION_ADDRESS = "TWiWt5SEDzaEqS6kE5gandWMNfxR2B5xzg"

class App extends Component {
  constructor(props) {
    super(props)

    this.state = {
      tronWeb: {
        installed: false,
        loggedIn: false
      }
    }
  }

  // test
  // https://github.com/TronWatch/TronLink-Demo-Messages/blob/master/src/components/App/index.js
  async componentDidMount() {
    await new Promise(resolve => {
      const tronWebState = {
        installed: !!window.tronWeb,
        loggedIn: window.tronWeb && window.tronWeb.ready
      }

      if (tronWebState.installed) {
        this.setState({
          tronWeb: tronWebState
        })

        return resolve()
      }

      let tries = 0

      const timer = setInterval(() => {
        if (tries >= 10) {
          const TRONGRID_API = "https://api.trongrid.io"

          window.tronWeb = new TronWeb(TRONGRID_API, TRONGRID_API, TRONGRID_API)

          this.setState({
            tronWeb: {
              installed: false,
              loggedIn: false
            }
          })

          clearInterval(timer)
          return resolve()
        }

        tronWebState.installed = !!window.tronWeb
        tronWebState.loggedIn = window.tronWeb && window.tronWeb.ready

        if (!tronWebState.installed) return tries++

        this.setState({
          tronWeb: tronWebState
        })

        resolve()
      }, 100)
    })

    if (!this.state.tronWeb.loggedIn) {
      // Set default address (foundation address) used for contract calls
      // Directly overwrites the address object as TronLink disabled the
      // function call
      window.tronWeb.defaultAddress = {
        hex: window.tronWeb.address.toHex(FOUNDATION_ADDRESS),
        base58: FOUNDATION_ADDRESS
      }

      window.tronWeb.on("addressChanged", () => {
        if (this.state.tronWeb.loggedIn) return

        this.setState({
          tronWeb: {
            installed: true,
            loggedIn: true
          }
        })
      })
    }
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            balance : {this.state.balance}
          </a>
        </header>
      </div>
    )
  }
}

export default App
