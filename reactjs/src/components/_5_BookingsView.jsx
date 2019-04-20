import React, {Component} from 'react';

class _5_BookingsView extends Component {

    constructor(props) {
        super(props);

        this.state = {
            bookings: []
        };

        this.fetchBookingsByUserEmail = this.fetchBookingsByUserEmail.bind(this);
    }

    async fetchBookingsByUserEmail(event) {
        event.preventDefault();
        const userEmail = document.getElementById("user-email-input").value;

        await fetch(`user/${userEmail}/bookings`).then(response => {
            if (response.ok) {
                response.json()
                    .then(json => {
                        this.setState({bookings: json});
                    })
            } else {
                this.setState({bookings: []})
            }
        });
    }

    render() {
        const bookings = this.state.bookings;

        return (
            <div>
                <h2>5. User can view his booking.</h2>
                <div className="wrapper">
                    <form onSubmit={this.fetchBookingsByUserEmail}>
                        <input id="user-email-input" type="text" name="email" placeholder="user email"/>
                        <input type="submit" name="submit"/>
                    </form>
                    <ul>
                        {bookings.map(b => (
                            <li className="user-bookings">
                                <p>{`User email:  ${b.user.email}`}</p>
                                <p>{`Room number: ${b.room.number}`}</p>
                                <p>{`Room type: ${b.room.name}`}</p>
                                <p>{`Room price: ${b.room.price}`}</p>
                                <p>{`From: ${b.dateFrom}`}</p>
                                <p>{`To: ${b.dateTo}`}</p>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    }
}


export default _5_BookingsView;
