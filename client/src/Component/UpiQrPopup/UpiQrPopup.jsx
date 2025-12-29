import "./UpiQrPopup.css";

const UpiQrPopup = ({ amount , onSuccess, onCancel }) => {
  return (
    <div className="upi-overlay">
      <div className="upi-box">
        <h5>Scan UPI QR</h5>

<h5 className="upi-amount">
          Pay ₹{amount.toFixed(2)} via UPI
        </h5>
        {/* Dummy QR Image */}
        <img
          src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=upi://pay"
          alt="UPI QR"
        />

        <p className="mt-2">Scan & complete payment</p>

        <div className="d-flex gap-2 mt-3">
          <button className="btn btn-success flex-grow-1" onClick={onSuccess}>
            Payment Done
          </button>
          <button className="btn btn-danger flex-grow-1" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpiQrPopup;
