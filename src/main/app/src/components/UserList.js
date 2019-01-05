import React, { Component } from 'react';
import { Table, Button, ButtonGroup  } from 'reactstrap';
import Loading from '../images/Loading.gif';
import { Link } from 'react-router-dom';

export default class UserList extends Component {

    constructor(props) {
        super(props);
        this.state = { users: [], isLoading: true };
        this.removeUser = this.removeUser.bind(this);
    }

    componentDidMount() {
        fetch('/users')
            .then(response => response.json())
            .then(resData => {
                this.setState({ users: resData.users, isLoading: false });
            })
    }

    removeUser(id) {
        fetch(`/users/${id}`, {
            method: 'DELETE',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json' }
        }).then(response => {
            let updatedUsers = [...this.state.users].filter(i => i.id !== id);
            this.setState({users: updatedUsers});
        });
    }

    render() {
        const {users, isLoading} = this.state;

        if (isLoading) {
            return (
                <div align="center" alt="Loading">
                    <img src={Loading}/>
                </div>
             );
        }
        return (
            <div>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/users/new">Add User</Button>
                </div>
                <h3>User Management</h3>
                <Table hover>
                    <thead>
                        <tr>
                            <th>USERNAME</th>
                            <th>FIRSTNAME</th>
                            <th>LASTNAME</th>
                            <th>AGE</th>
                            <th>EMAIL</th>
                            <th>ACTION</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map(user =>
                            <tr key={user.id}>
                                <td>{user.userName}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.age}</td>
                                <td>{user.emailAddress}</td>
                                <td>
                                  <ButtonGroup>
                                    <Button size="sm" color="danger" onClick={() => this.removeUser(user.id)}>Delete</Button>
                                  </ButtonGroup>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </Table>
            </div>
        )
    }
}
