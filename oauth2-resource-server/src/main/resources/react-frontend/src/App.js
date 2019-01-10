import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { UserList, UserEdit, Header, Home } from './components';
import {Container} from 'reactstrap';


export default class App extends Component {

  render() {
    return (
        <Router>
          <div>
            <Header/>
            <Container fluid>
                <Route path='/' exact={true} component={Home}/>
                <Route path='/users' exact={true} component={UserList}/>
                <Route path='/users/:id' exact={true} component={UserEdit}/>
             </Container>
          </div>
        </Router>
    );
  }
}

