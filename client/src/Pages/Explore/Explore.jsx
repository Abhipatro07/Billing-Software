import { useContext, useState } from 'react';
import './Explore.css'
import { AppContext } from '../../Context/AppContext';
import DisplayCategory from '../../Component/DisplayCategory/DisplayCategory';
import DisplayItems from '../../Component/DisplayItems/DisplayItems';
import CustomerForm from '../../Component/CustomerForm/CustomerForm';
import CartItems from '../../Component/CartItems/CartItems';
import CartSummary from '../../Component/CartSummary/CartSummary';

const Explore = () => {
    const {categories} = useContext(AppContext)
    const [selectedCategory , setSelectedCategory] = useState("")
    const [customerName , setCustomerName] = useState("")
    const [mobileNumber , setMobileNumber] = useState("")
    
    return(
        <div className="explore-container text-light">
            <div className="left-column">
                <div className="first-row" style={{overflowY:"auto"}}>
                    <DisplayCategory categories={categories}
                        selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory}/>
                </div>
                <hr className="my-3 text-light"/>
                <div className="second-row" style={{overflowY:"auto"}}>
                    <DisplayItems selectedCategory={selectedCategory}/>
                </div>
            </div>
            <div className="right-column d-flex flex-column">
                <div className="customer-form-container" >
                    <CustomerForm 
                        customerName = {customerName}
                        mobileNumber = {mobileNumber}
                        setCustomerName = {setCustomerName}
                        setMobileNumber = {setMobileNumber}
                    />
                </div>
                <br />
                <hr className="my-3 text-light"/>
                <div className="cart-item-container" >
                    <CartItems />
                </div>
                <hr className="my-3 text-light"/>
                <div className="cart-summary-container" >
                    <CartSummary 
                        customerName = {customerName}
                        mobileNumber = {mobileNumber}
                        setCustomerName = {setCustomerName}
                        setMobileNumber = {setMobileNumber}
                    />
                </div>  
            </div>
        </div>
    )
}

export default Explore;