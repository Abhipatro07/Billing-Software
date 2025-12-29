import { useContext, useState } from "react";
import "./CartSummary.css";
import { AppContext } from "../../Context/AppContext";
import ReceiptPopup from "../../Component/ReceiptPopup/ReceiptPopup";
import UpiQrPopup from "../../Component/UpiQrPopup/UpiQrPopup";
import { createOrder, deleteOrder } from "../../Service/OrderService";
import { verifyPayment } from "../../Service/PaymentService";
import toast from "react-hot-toast";

export const CartSummary = ({
  customerName,
  mobileNumber,
  setCustomerName,
  setMobileNumber,
}) => {
  const { cartItems, clearCart } = useContext(AppContext);

  const [isProcessing, setIsProcessing] = useState(false);
  const [orderDetails, setOrderDetails] = useState(null);
  const [pendingOrder, setPendingOrder] = useState(null);
  const [showPopup, setShowPopup] = useState(false);
  const [showUpiQr, setShowUpiQr] = useState(false);

  const totalAmount = cartItems.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );

  const tax = totalAmount * 0.01;
  const grandTotal = totalAmount + tax;

  const clearAll = () => {
    clearCart();
    setCustomerName("");
    setMobileNumber("");
  };

 const handlePrintReceipt = () => {
  const printContents = document.querySelector(".receipt-container").innerHTML;
  const originalContents = document.body.innerHTML;

  document.body.innerHTML = `
    <html>
      <head>
        <title>Receipt</title>
        <style>
          body {
            font-family: Arial, sans-serif;
            padding: 20px;
          }
        </style>
      </head>
      <body>
        ${printContents}
      </body>
    </html>
  `;

  window.print();
  window.location.reload(); // restore UI
};

  // ================= CASH =================
  const handleCashPayment = async () => {
    if (!customerName || !mobileNumber) {
      toast.error("Enter customer details");
      return;
    }

    if (cartItems.length === 0) {
      toast.error("Cart is empty");
      return;
    }

    setIsProcessing(true);

    try {
      const res = await createOrder({
        customerName,
        phoneNumber: mobileNumber,
        cartItems,
        subTotal: totalAmount,
        tax,
        grandTotal,
        paymentMethod: "CASH",
      });

      setOrderDetails(res.data);
      setShowPopup(true);
    //   clearAll();

      toast.success("Cash payment successful");
    } finally {
      setIsProcessing(false);
    }
  };

  // ================= UPI =================
  const handleUpiPayment = async () => {
    if (!customerName || !mobileNumber) {
      toast.error("Enter customer details");
      return;
    }

    if (cartItems.length === 0) {
      toast.error("Cart is empty");
      return;
    }

    setIsProcessing(true);

    try {
      const res = await createOrder({
        customerName,
        phoneNumber: mobileNumber,
        cartItems,
        subTotal: totalAmount,
        tax,
        grandTotal,
        paymentMethod: "UPI",
      });

      setPendingOrder(res.data);
      setShowUpiQr(true); // ✅ SHOW QR
    } finally {
      setIsProcessing(false);
    }
  };

  // ================= QR SUCCESS =================
  const handleUpiSuccess = async () => {
  try {
    const response = await verifyPayment({
      razorpayOrderId: "dummy_order",
      razorpayPaymentId: "pay_" + Date.now(),
      razorpaySignature: "dummy_signature",
      orderId: pendingOrder.orderId,
    });

    // ✅ USE UPDATED ORDER FROM BACKEND
    setOrderDetails(response.data);
    setShowPopup(true);

    toast.success("UPI payment successful");
  } finally {
    setShowUpiQr(false);
    setPendingOrder(null);
  }
};


  // ================= QR CANCEL =================
  const handleUpiCancel = async () => {
    if (pendingOrder) {
      await deleteOrder(pendingOrder.orderId);
    }
    toast.error("UPI cancelled");
    setShowUpiQr(false);
    setPendingOrder(null);
  };

  const placeOrder = () => {
    clearAll();
    setShowPopup(false);
    setOrderDetails(null);
  };

  return (
    <div className="mt-2">
      {/* summary */}
      <div className="cart-summary-details">
        <div className="d-flex justify-content-between mb-2">
          <span>Item:</span>
          <span>₹{totalAmount.toFixed(2)}</span>
        </div>
        <div className="d-flex justify-content-between mb-2">
          <span>Tax:</span>
          <span>₹{tax.toFixed(2)}</span>
        </div>
        <div className="d-flex justify-content-between mb-4">
          <span>Total:</span>
          <span>₹{grandTotal.toFixed(2)}</span>
        </div>
      </div>

      {/* buttons */}
      <div className="d-flex gap-3">
        <button className="btn btn-success flex-grow-1" onClick={handleCashPayment}>
          Cash
        </button>
        <button className="btn btn-primary flex-grow-1" onClick={handleUpiPayment}>
          UPI
        </button>
      </div>

      <div className="d-flex gap-3 mt-3">
        <button className="btn btn-warning flex-grow-1" onClick={placeOrder} disabled={showPopup}>
          Place Order
        </button>
      </div>

      {showPopup && <ReceiptPopup orderDetails={{
        ...orderDetails , 
        razorpayOrderId: orderDetails.paymentDetails?.razorpayOrderId,
        razorpayPaymentId: orderDetails.paymentDetails?.razorpayPaymentId,
      }}onClose={() => setShowPopup(false)} onPrint={handlePrintReceipt}/>}
      {showUpiQr && <UpiQrPopup amount={grandTotal} onSuccess={handleUpiSuccess} onCancel={handleUpiCancel} />}
    </div>
  );
};

export default CartSummary;
