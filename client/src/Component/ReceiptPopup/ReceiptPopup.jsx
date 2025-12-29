import "./ReceiptPopup.css";

const ReceiptPopup = ({ orderDetails, onClose, onPrint }) => {
  if (!orderDetails) return null;

  const paymentDetails = orderDetails.paymentDetails || {};

  return (
    <div className="receipt-overlay">
      {/* 👇 print-area is REQUIRED */}
      <div className="receipt-container print-area text-dark">

        <div className="text-center mb-3">
          <i className="bi bi-check-circle-fill txt-success fs-1"></i>
        </div>

        <h3 className="text-center mb-4">Order Receipt</h3>

        <p><strong>Order ID:</strong> {orderDetails.orderId}</p>
        <p><strong>Name:</strong> {orderDetails.customerName}</p>
        <p><strong>Mobile:</strong> {orderDetails.phoneNumber}</p>

        <hr />

        <h5 className="mb-3">Items Ordered</h5>

        <div className="cart-items-scrollable">
          {orderDetails.items.map((item, index) => (
            <div key={index} className="d-flex justify-content-between mb-2">
              <span>{item.name} × {item.quantity}</span>
              <span>₹{(item.price * item.quantity).toFixed(2)}</span>
            </div>
          ))}
        </div>

        <hr />

        <div className="d-flex justify-content-between mb-2">
          <strong>Subtotal:</strong>
          <span>₹{orderDetails.subTotal.toFixed(2)}</span>
        </div>

        <div className="d-flex justify-content-between mb-2">
          <strong>Tax (1%):</strong>
          <span>₹{orderDetails.tax.toFixed(2)}</span>
        </div>

        <div className="d-flex justify-content-between mb-2">
          <strong>Grand Total:</strong>
          <span>₹{orderDetails.grandTotal.toFixed(2)}</span>
        </div>

        <p className="mt-2">
          <strong>Payment Method:</strong> {orderDetails.paymentMethod}
        </p>

        {/* ✅ UPI DETAILS */}
        {orderDetails.paymentMethod === "UPI" && (
          <>
            <p>
              <strong>Razorpay Order ID:</strong>{" "}
              {paymentDetails.razorpayOrderId || "-"}
            </p>
            <p>
              <strong>Razorpay Payment ID:</strong>{" "}
              {paymentDetails.razorpayPaymentId || "-"}
            </p>
          </>
        )}

        {/* ✅ CASH STATUS */}
        {orderDetails.paymentMethod === "CASH" && (
          <p><strong>Status:</strong> Paid in Cash</p>
        )}

        {/* 👇 no-print is REQUIRED */}
        <div className="no-print d-flex justify-content-end gap-3 mt-4">
          <button className="btn btn-warning" onClick={onPrint}>
            Print Receipt
          </button>
          <button className="btn btn-danger" onClick={onClose}>
            Close
          </button>
        </div>

      </div>
    </div>
  );
};

export default ReceiptPopup;
