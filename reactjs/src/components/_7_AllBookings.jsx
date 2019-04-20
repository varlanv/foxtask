import React, {Component} from 'react';

class _7_AllBookings extends Component {

    constructor(props) {
        super(props);

        this.state = {
            bookings: [],
        };
    }

    async componentWillMount() {
        const request = await fetch("/bookings");
        const body = await request.json();

        this.setState({
            bookings: body
        });

    }

    render() {
        const {bookings} = this.state;

        return (
            <div className="wrapper">
                <ul>
                    <h2>7. View all bookings for the hotel.

                    </h2>
                    {bookings.map(booking => (
                        <li>
                            {JSON.stringify(booking)}
                        </li>
                    ))}
                </ul>
            </div>
        );
    }
}

export default _7_AllBookings;
