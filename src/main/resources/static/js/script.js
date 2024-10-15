document.addEventListener("DOMContentLoaded", function () {
  var deleteModal = document.getElementById("deleteModal");
  var deleteButtons = document.querySelectorAll(".delete-btn");
  var vehicleRegistrationNumberSpan = document.getElementById(
    "vehicleRegistrationNumber"
  );
  var deleteForm = document.getElementById("deleteForm");

  deleteButtons.forEach(function (button) {
    button.addEventListener("click", function () {
      var registrationNumber = this.getAttribute("data-registration-number");
      vehicleRegistrationNumberSpan.textContent = registrationNumber;
      deleteForm.action = "/vehicle/" + registrationNumber;
    });
  });

  // Bootstrap modal event listener
  deleteModal.addEventListener("show.bs.modal", function (event) {
    var button = event.relatedTarget;
    var registrationNumber = button.getAttribute("data-registration-number");
    vehicleRegistrationNumberSpan.textContent = registrationNumber;
    deleteForm.action = "/vehicle/" + registrationNumber;
  });
});
