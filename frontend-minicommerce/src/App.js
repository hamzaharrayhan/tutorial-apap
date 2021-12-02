import React, { Component } from "react";
import Layout from "./components/layout";
import ItemList from "./Containers/ItemList";
class App extends Component {
  render() {
    return (
      <Layout>
        <ItemList />
      </Layout>
    );
  }
}
export default App;
