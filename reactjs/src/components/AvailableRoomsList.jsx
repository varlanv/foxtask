import React, {Component} from 'react';

class AvailableRoomsList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            rooms: [],
            services: [],
            roomsByCategory: []
        };
    }

    async componentWillMount() {
        const request = await fetch("/rooms");
        const body = await request.json();

        this.setState({
            rooms: body
        });

        const services = await fetch("/extra-services");
        const bod = await services.json();

        this.setState({
            services: bod
        });
        const roomByCategory = await fetch("/rooms/category/LUX");
        const bode = await roomByCategory.json();

        this.setState({
            roomsByCategory: bode
        })
    }


    render() {
        const {rooms} = this.state;
        const {services} = this.state;
        const {roomsByCategory} = this.state

        return (
            <div>
                <ul>
                    {rooms.map(room => (
                        <li>
                            {room.number} {room.available + ""} {room.name} {room.price}
                        </li>
                    ))}
                </ul>

                {/*<ul>*/}
                    {/*{services.map(service => (*/}
                        {/*<li>*/}
                            {/*{service.name} {service.price}*/}
                        {/*</li>*/}
                    {/*))}*/}
                {/*</ul>*/}

                {/*<ul>*/}
                    {/*{roomsByCategory.map(room => (*/}
                        {/*<li>*/}
                            {/*{room.number} {room.price}*/}
                        {/*</li>*/}
                    {/*))}*/}
                {/*</ul>*/}

            </div>
        );
    }
}

export default AvailableRoomsList;
