import React, {Component} from 'react';

class _1_RoomList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            rooms: [],
        };
    }

    async componentWillMount() {
     await fetch("/rooms")
            .then(response => {
                if (response.ok) {
                    response.json()
                        .then(json => {
                            this.setState({rooms: json});
                        })
                } else {
                    this.setState({rooms: []})
                }
            });
    }

    render() {
        const {rooms} = this.state;

        return (
            <div>
                <h2>1. View list of available rooms (room have a number, category, price, additional options like
                    breakfast, cleaning with additional cost) for specified dates.
                </h2>
                <div className="wrapper">
                    <ul>

                        {rooms.map(room => (
                            <li className="room-list">
                                <p>{`Room number: ${room.number}`}</p>
                                <p>{`Room type: ${room.name}`}</p>
                                <p>{`Room price: ${room.price}`}</p>
                                <p>{`Available: ${room.available}`}</p>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    }
}

export default _1_RoomList;
