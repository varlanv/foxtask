import React, {Component} from 'react';

class _3_CreateUser extends Component {

    constructor(props) {
        super(props);

        this.state = {
            email: ""
        };

        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handleCreateUser = this.handleCreateUser.bind(this);
    }

    handleEmailChange(event) {
        this.setState({
            email: event.target.value
        })

    }

    async handleCreateUser(event) {
        event.preventDefault();

        const req = await fetch("/user", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSON.stringify(this.state)
        })

    }

    render() {

        return (
            <div className="wrapper">
                <h2>3. Create user.</h2>
                <form onSubmit={this.handleCreateUser}>
                    <input type="text" name="email" value={this.state.email} onChange={this.handleEmailChange}/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        );
    }

}

export default _3_CreateUser;
