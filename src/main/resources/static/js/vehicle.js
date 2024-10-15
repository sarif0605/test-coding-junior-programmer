$("#vehicle-table").DataTable({
  ajax: {
    url: "api/vehicle",
    dataSrc: "",
  },
  columns: [
    {
      data: null,
      render: (data, type, row, meta) => {
        return meta.row + 1;
      },
    },
    {
      data: "registrationNumber",
    },
    {
      data: "ownerName",
    },
    {
      data: "brand",
    },
    {
      data: "manufacturingYear",
    },
    {
      data: "cylinderCapacity",
      render: function render(data) {
        return `${data} cc`;
      },
    },
    {
      data: "color",
    },
    {
      data: "fuelType",
    },
    {
      data: null,
      render: function (data, type, row, meta) {
        return `
          <button
            type="button"
            class="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#detail"
            onclick="getDetail('${data.registrationNumber}')"
          >
            <i class="fa-solid fa-circle-info"></i>
          </button>
          <button
            type="button"
            class="btn btn-warning"
            data-bs-toggle="modal"
            data-bs-target="#edit"
            onclick="editVehicle('${data.registrationNumber}')"
          >
            <i class="fa-regular fa-pen-to-square"></i>
          </button>
          <button
            type="button"
            class="btn btn-danger"
            onclick="deleteVehicle('${data.registrationNumber}')"
          >
            <i class="fa-solid fa-trash-can"></i>
          </button>
        `;
      },
    },
  ],
});

// Function to get vehicle details
function getDetail(registrationNumber) {
  $.ajax({
    method: "GET",
    url: "api/vehicle/" + registrationNumber,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#detail-registrationNumber").val(res.registrationNumber);
      $("#detail-ownerName").val(res.ownerName);
      $("#detail-address").val(res.address);
      $("#detail-brand").val(res.brand);
      $("#detail-manufacturingYear").val(res.manufacturingYear);
      $("#detail-cylinderCapacity").val(res.cylinderCapacity);
      $("#detail-color").val(res.color);
      $("#detail-fuelType").val(res.fuelType);
    },
  });
}
// Function to create a new vehicle
createVehicle = () => {
  let registrationNumber = $("#create-registrationNumber").val();
  let ownerName = $("#create-ownerName").val();
  let address = $("#create-address").val();
  let brand = $("#create-brand").val();
  let manufacturingYear = $("#create-manufacturingYear").val();
  let cylinderCapacity = $("#create-cylinderCapacity").val();
  let color = $("#create-color").val();
  let fuelType = $("#create-fuelType").val();

  $.ajax({
    method: "POST",
    url: "api/vehicle",
    dataType: "JSON",
    contentType: "application/json",
    data: JSON.stringify({
      registrationNumber,
      ownerName,
      address,
      brand,
      manufacturingYear,
      cylinderCapacity,
      color,
      fuelType,
    }),
    success: (res) => {
      Swal.fire({
        icon: "success",
        title: "Success",
        text: "Vehicle data added successfully!",
      });
      console.log("Success response:", res);
      $("#create").modal("hide");
      $("#vehicle-table").DataTable().ajax.reload();
      $("#create-form")[0].reset();
    },
    error: (err) => {
      console.log("Error response:", err);
      const errorMessage =
        err.responseJSON?.messages || "Failed to add vehicle data.";

      Swal.fire({
        icon: "error",
        title: "Error",
        text: errorMessage,
      });
    },
  });
};

// Function to edit a vehicle
function editVehicle(registrationNumber) {
  $.ajax({
    method: "GET",
    url: "api/vehicle/" + registrationNumber,
    dataType: "JSON",
    success: (res) => {
      $("#edit-registrationNumber").val(res.registrationNumber);
      $("#edit-ownerName").val(res.ownerName);
      $("#edit-address").val(res.address);
      $("#edit-brand").val(res.brand);
      $("#edit-manufacturingYear").val(res.manufacturingYear);
      $("#edit-cylinderCapacity").val(res.cylinderCapacity);
      $("#edit-color").val(res.color);
      $("#edit-fuelType").val(res.fuelType);
    },
  });
}

updateVehicle = () => {
  let registrationNumber = $("#edit-registrationNumber").val();
  let ownerName = $("#edit-ownerName").val();
  let address = $("#edit-address").val();
  let brand = $("#edit-brand").val();
  let manufacturingYear = $("#edit-manufacturingYear").val();
  let cylinderCapacity = $("#edit-cylinderCapacity").val();
  let color = $("#edit-color").val();
  let fuelType = $("#edit-fuelType").val();

  $.ajax({
    method: "PUT",
    url: "api/vehicle/" + registrationNumber,
    dataType: "JSON",
    contentType: "application/json",
    data: JSON.stringify({
      registrationNumber,
      ownerName,
      address,
      brand,
      manufacturingYear,
      cylinderCapacity,
      color,
      fuelType,
    }),
    success: (res) => {
      Swal.fire({
        icon: "success",
        title: "Success",
        text: "Vehicle data updated successfully!",
      });
      $("#edit").modal("hide");
      $("#vehicle-table").DataTable().ajax.reload();
    },
    error: (err) => {
      console.log("Error response:", err);
      const errorMessage =
        err.responseJSON?.messages || "Failed to add vehicle data.";
      Swal.fire({
        icon: "error",
        title: "Error",
        text: errorMessage,
      });
    },
  });
};

// Function to delete a vehicle with SweetAlert
function deleteVehicle(registrationNumber) {
  Swal.fire({
    title: "Apakah Anda Yakin?",
    text: `Anda yakin menghapus data ${registrationNumber}?`,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#3085d6",
    confirmButtonText: "Yes, delete it!",
    cancelButtonText: "Cancel",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        method: "DELETE",
        url: "api/vehicle/" + registrationNumber,
        success: () => {
          Swal.fire({
            icon: "success",
            title: "Deleted!",
            text: `Vehicle with registration number ${registrationNumber} has been deleted.`,
            timer: 2000,
          });
          $("#vehicle-table").DataTable().ajax.reload();
        },
        error: () => {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Failed to delete the vehicle. Please try again later.",
          });
        },
      });
    }
  });
}
