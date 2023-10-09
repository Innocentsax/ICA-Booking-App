// Select elements from the HTML
const gridRows = document.querySelectorAll(".grid-row");
const selectedSeatsElement = document.getElementById("selected-seats");
const totalPriceElement = document.getElementById("total-price");
const nonIcaTotalPriceElement = document.getElementById("non-ica-total-price");
const vvipSeatElement = document.getElementById("vvip-seat");
const vip1SeatElement = document.getElementById("vip1-seat");
const vip2SeatElement = document.getElementById("vip2-seat");
const normalSeatElement = document.getElementById("normal-seat");
const fullName = document.querySelector("#fullname");
const email = document.querySelector("#email");
const phoneNumber = document.querySelector("#phone");
const ICANumber1 = document.querySelector("#ica-number1");
const ICANumber = document.querySelector("#ica-number");
const form = document.querySelector(".form");
const table = document.querySelector(".table");
const formContainer = document.querySelector(".form-container");
const bookingContainer = document.querySelector(".booking-container");
const proceedBtn = document.querySelector(".btn-proceed");
const backBtn = document.querySelector(".btn-back");
const checkbox = document.querySelector(".checkbox");
const seatNumberInput = document.querySelector("#seat-number");
const ticketCategoryInput = document.querySelector("#ticket-category");
const paymentInfo = document.querySelector(".payment-info")

async function fetchSeatData() {
  try {
    const response = await fetch("http://localhost:8091/api/allSeatsInfo", {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET",
    });
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
  }
}
let allSeatStatus;
window.onload = async () => {
  try {
    allSeatStatus = await fetchSeatData();
    console.log(allSeatStatus);
  } catch (error) {
    console.log(error);
  }

  allSeatStatus.vvip.forEach((data, dataIndx) => {
    document.querySelectorAll(".vvip-seat").forEach((seat, seatIndx) => {
      if(dataIndx === seatIndx) {
        if (data.status === 1) seat.classList.add("selected-pending");
        else if(data.status === 2) seat.classList.add("occupied");
      }
    })
  })
};

console.log(allSeatStatus);

// Initialize selected seat count and seat price
let selectedSeatCount = 0;
// const seatPrice = 10;

// Define seat prices for each section
const normalSeatPrices = {
  "vvip-seat": 160000, // Price for VVIP seats
  "vip1-seat": 110000, // Price for VIP 1 seats
  "vip2-seat": 70000, // Price for VIP 2 seats
  "normal-seat": 40000, // Price for Normal seats
};
const discountSeatPrices = {
  "vvip-seat": 150000, // Price for VVIP seats
  "vip1-seat": 100000, // Price for VIP 1 seats
  "vip2-seat": 60000, // Price for VIP 2 seats
  "normal-seat": 35000, // Price for Normal seats
};

// Define the number of grids per row
const numGridsPerRowVIP = 8; // Adjust the number of grids per row for VIP
const numGridsPerRowNormal = 10; // Adjust the number of grids per row for Normal

// Define the number of rows and seats in each grid for regular and VIP sections
const rowsPerGrid = 4; // Number of rows in each grid for both VIP and Normal
const seatsPerRow = [3, 3, 3, 1]; // Number of seats in each row per grid for both VIP and Normal

// Initialize seat number counter
let seatNumberCounter = 1;

const bookingDetails = {
  selectedSeats: [],
  ICANumber: 0,
  ticketCategories: [],
  cost: 0,
};

const request = {
  attendees: [],
};

document.querySelectorAll(".seat").forEach(seat => {
  seat.addEventListener("click", () => {
    if (!seat.classList.contains("occupied")) {
      seat.classList.toggle("selected");
      updateSelectedSeats(seat);
    }
  })
})



// Function to update selected seats and total price
function updateSelectedSeats(seat) {
  const selectedNormalSeats = document.querySelectorAll(
      ".seat.normal-seat.selected"
  );
  const selectedVVIPSeats = document.querySelectorAll(
      ".seat.vvip-seat.selected"
  );
  const selectedVIP1Seats = document.querySelectorAll(
      ".seat.vip1-seat.selected"
  );
  const selectedVIP2Seats = document.querySelectorAll(
      ".seat.vip2-seat.selected"
  );
  console.log(selectedVIP1Seats);

  const selectedNormalSeatCount = selectedNormalSeats.length;
  const selectedVVIPSeatCount = selectedVVIPSeats.length;
  const selectedVIP1SeatCount = selectedVIP1Seats.length;
  const selectedVIP2SeatCount = selectedVIP2Seats.length;

  selectedSeatCount =
      selectedNormalSeatCount +
      selectedVVIPSeatCount +
      selectedVIP1SeatCount +
      selectedVIP2SeatCount;
  selectedSeatsElement.textContent = selectedSeatCount;

  let totalPrice = 0;
  let nonIcaTotalPrice = 0;

  const ticketCategory = seat.classList.contains("vvip-seat")
      ? "VVIP"
      : seat.classList.contains("vip1-seat")
          ? "VIP1"
          : seat.classList.contains("vip2-seat")
              ? "VIP2"
              : "NORMAL";

  selectedNormalSeats.forEach((seat) => {
    const seatClass = seat.classList[1]; // Get the second class, which corresponds to seat type
    const price = normalSeatPrices[seatClass];
    const ICAPrice = discountSeatPrices[seatClass];
    totalPrice += ICAPrice;
    nonIcaTotalPrice += price;
    console.log(totalPrice);
  });

  selectedVVIPSeats.forEach((seat) => {
    const seatClass = seat.classList[1]; // Get the second class, which corresponds to seat type
    const price = normalSeatPrices[seatClass];
    const ICAPrice = discountSeatPrices[seatClass];
    totalPrice += ICAPrice;
    nonIcaTotalPrice += price;
    console.log(totalPrice);
  });

  selectedVIP1Seats.forEach((seat) => {
    const seatClass = seat.classList[1]; // Get the second class, which corresponds to seat type
    const price = normalSeatPrices[seatClass];
    const ICAPrice = discountSeatPrices[seatClass];
    totalPrice += ICAPrice;
    nonIcaTotalPrice += price;
    console.log(totalPrice);
  });

  selectedVIP2Seats.forEach((seat) => {
    const seatClass = seat.classList[1]; // Get the second class, which corresponds to seat type
    const price = normalSeatPrices[seatClass];
    const ICAPrice = discountSeatPrices[seatClass];
    totalPrice += ICAPrice;
    nonIcaTotalPrice += price;
    console.log(totalPrice);
  });

  if (bookingDetails.selectedSeats.includes(seat.dataset.seatNumber)) {
    bookingDetails.selectedSeats.splice(
        bookingDetails.selectedSeats.indexOf(seat.dataset.seatNumber),
        1
    );
  } else {
    bookingDetails.selectedSeats.push(seat.dataset.seatNumber);
  }

  if (!bookingDetails.ticketCategories.includes(ticketCategory)) {
    bookingDetails.ticketCategories.push(ticketCategory);
  }

  console.log(seat.dataset);

  bookingDetails.ICANumber = ICANumber.value;

  checkbox.checked
      ? (bookingDetails.cost = totalPrice)
      : (bookingDetails.cost = nonIcaTotalPrice);
  console.log(bookingDetails);

  totalPriceElement.textContent = totalPrice;
  console.log(totalPriceElement.textContent);
  nonIcaTotalPriceElement.textContent = nonIcaTotalPrice;
  console.log(nonIcaTotalPriceElement.textContent);
  normalSeatElement.textContent = selectedNormalSeatCount;
  vvipSeatElement.textContent = selectedVVIPSeatCount;
  vip1SeatElement.textContent = selectedVIP1SeatCount;
  vip2SeatElement.textContent = selectedVIP2SeatCount;
}

console.log(form);

let totalCost = 0;


form.addEventListener("submit", (e) => {
  e.preventDefault();

  const booking = {
    name: "",
    phoneNumber: "",
    email: "",
    cardNumber: "",
    origin: false,
    ticketCategory: "",
    seatNo: 0,
  };

  booking.name = fullName.value;
  booking.email = email.value;
  booking.phoneNumber = phoneNumber.value;
  booking.cardNumber = ICANumber1.value === "" ? "0" : ICANumber1.value;
  booking.origin = request.attendees.length <= 0;
  booking.seatNo = seatNumberInput.value;
  booking.ticketCategory = ticketCategoryInput.value;
  request.attendees.push(booking);
  totalCost += bookingDetails.cost;

  const tableRow = document.createElement("tr");
  tableRow.innerHTML = `
  <td>${fullName.value}</td>
  <td>${email.value}</td>
  <td>${phoneNumber.value}</td>
  <td>${ICANumber1.value}</td>
  <td>${seatNumberInput.value}</td>
  <td>${ticketCategoryInput.value}</td>
  `;

  table.appendChild(tableRow);

  fullName.value = "";
  phoneNumber.value = "";
  ICANumber1.value = "";
  email.value = "";

  //   console.log(booking);
  console.log(request);
});

proceedBtn.addEventListener("click", (e) => {
  e.preventDefault();
  formContainer.style.display = "block";
  bookingContainer.style.display = "none";

  // formContainer.classList.toggle("hidden");
  // bookingContainer.classList.toggle("visible");

  ICANumber1.value = bookingDetails.ICANumber;

  bookingDetails.selectedSeats.forEach((num) => {
    seatNumberInput.innerHTML += `<option value="${num}">${num}</option>`;
  });
  bookingDetails.ticketCategories.forEach((category) => {
    ticketCategoryInput.innerHTML += `<option value="${category}">${category}</option>`;
  });
  console.log(seatNumberInput);
});

backBtn.addEventListener("click", (e) => {
  e.preventDefault();
  formContainer.style.display = "none";
  bookingContainer.style.display = "block";
});

const request1 = {
  attendees: [],
  totalCost: 0
}


async function saveBookingData() {
  request1.attendees = request.bookings;
  request1.totalCost = request.totalCost;
  const data = {
    attendees: request.attendees,
  }
  try {
    const response = await fetch("http://localhost:8091/api/saveBooking", {
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
      body: JSON.stringify(data)
    });
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    console.log(data)
    return await response.json();
  } catch (error) {
    console.error("Error:", error);
  }
}


  document.querySelector(".btn-ticket").addEventListener("click", async (e) => {
    const response = await saveBookingData();
    if (response) {
      console.log(response)
      formContainer.style.display = "none";
      paymentInfo.style.display = "flex";
      const notice = `
        <div class="ticket">
        <div class="main-ticket">
        <div class="logo-box2">
        <img src="img/IMG_1341.jpg" class="logo">
        <span>Indian Cultural <span class="logo-textd">Association</span></span>
        </div>
        
        <div class="ticket-item"><span class="title">Name:</span>${response.name}</div>
        <div class="ticket-item"><span class="title">Email:</span>${response.email}</div>
        <div class="ticket-item"><span class="title">Seat Number:</span>${response.seatNo}</div>
        <div class="ticket-item"><span class="title">Ticket Categories:</span>${response.ticketCategory}</div>
        <div class="ticket-item"><span class="title">TicketId:</span>${response.ticketId}</div>
        <div class="ticket-item"><span class="title">Date/Time:</span><span class="date">4th November 2023</span> <span>18:00pm</span></div>
        <div class="ticket-item"><span class="title">Venue:</span>Garki 900103, Abuja, Federal Capital Territory</div>
        <div class="ticket-item"><span class="title">Total Cost:</span>${response.totalCost}</div>
        </div>
        <p>
        <span class="pay-to">Pay <span class="cost">₦${totalCost}</span> to ICA account number:</span>
        <br />
        <b>Bank</b>: Access Bank
        <br />
        <b>Account Number</b>: 0003328229
        <br />
        <b>Account Name</b>: Indian Cultural Association
        <br />
        <span class="note"><strong>Note:</strong> <em>You need to pay within 6 hours otherwise your
        booking will cancel</em></span>
      </p>
        </div>
        
      `;
      paymentInfo.innerHTML = notice;
    }
  })
