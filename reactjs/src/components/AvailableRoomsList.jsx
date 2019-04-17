import React, {Component} from 'react';

class AvailableRoomsList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            rooms: []
        };
    }

    async componentWillMount() {
        const request = await fetch("/rooms");
        const body = await request.json();

        this.setState({
            rooms: body
        })
    }


    render() {
        const {rooms} = this.state;

        return (
            <div>
                <ul>
                    {rooms.map(room => (
                        <li>
                            {room.number} {room.available + ""} {room.name} {room.price}
                        </li>
                    ))}
                </ul>
            </div>
        );
    }
}

export default AvailableRoomsList;
