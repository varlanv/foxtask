import React, {Component} from 'react';

class _4_Booking extends Component {

    constructor(props) {
        super(props);

        this.state = {
            dateFrom: "",
            dateTo: "",
            email: "",
            roomNumber: "",
            services: []
        };

        this.handleBook = this.handleBook.bind(this);
        this.handleCheckBox = this.handleCheckBox.bind(this);
        this.handleDateFrom = this.handleDateFrom.bind(this);
        this.handleDateTo = this.handleDateTo.bind(this);
        this.handleEmail = this.handleEmail.bind(this);
        this.handleRoomNumber = this.handleRoomNumber.bind(this);
    }

        async handleBook(event) {
        event.preventDefault();
        const req = await fetch("/book", {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(this.state)
        })
    }

    handleCheckBox(event) {
        const {services} = this.state;
        const service = event.target.value;

        let newArr = [];

        if (!services.includes(service)) {
            newArr = [...services, service]
        } else {
            newArr = services.filter(a => a !== service);
        }

        this.setState({
            services: newArr
        })
    }

    handleDateFrom(e) {
        this.setState({
            dateFrom: e.target.value
        })
    }

    handleDateTo(e) {
        this.setState({
            dateTo: e.target.value
        })
    }

    handleEmail(e) {
        this.setState({
            userEmail: e.target.value
        })
    }

    handleRoomNumber(e) {
        this.setState({
            roomNumber: e.target.value
        })
    }

    render() {
        const {services} = this.props;

        return (
            <div className="wrapper">
                <h2>4. User can book the room for specified days.
                </h2>
                <form onSubmit={this.handleBook}>
                    <div>
                        <label>Booking date from:
                            <input type="date" name="dateFrom" min="2019-04-18" max="2019-12-31"
                                   onChange={this.handleDateFrom} required/>
                        </label>
                        <label>Booking date to:
                            <input type="date" name="dateTo" min="2019-04-18" max="2019-12-31"
                                   onChange={this.handleDateTo} required/>
                        </label>
                        <label>Email:
                            <input type="text" name="userEmail" onChange={this.handleEmail} required/>
                        </label>
                        <label>Room number:
                            <input type="number" name="roomNumber" onChange={this.handleRoomNumber} required/>
                        </label>
                        <label>
                            Services:
                            {services.map(serv => (
                                <label> {serv}
                                    <input type="checkbox" name="services" value={serv}
                                           onChange={this.handleCheckBox.bind(this)}/>
                                </label>
                            ))}
                        </label>
                    </div>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        );
    }
}

export default _4_Booking;