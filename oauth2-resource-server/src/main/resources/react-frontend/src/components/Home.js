import React, { Component } from 'react';
import { Jumbotron, Button} from 'reactstrap';

export default class Home extends Component {

  render() {
    return (
        <Jumbotron>
            <h1 className="display-3">Spring Boot & ReactJS integration</h1>
            <p className="lead">This sample spring boot application demonstrates nowadays used technologies with some CRUD operations</p>
            <hr className="my-2" />
            <p>Used technologies/frameworks: </p>
            <ul>
                <li>Spring Boot</li>
                <li>Swagger</li>
                <li>ReactJS</li>
            </ul>
        </Jumbotron>
    );
  }
}