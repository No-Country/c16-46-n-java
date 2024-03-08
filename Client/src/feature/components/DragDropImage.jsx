import React, { useState } from "react";

const imgConfig = {
  format: ["image/jpeg", "image/jpg", "image/png"],
  maxSize: 2,
};

const DragDropImage = ({handleImages}) => {
  const [error, setError] = useState("");

  const [imageFiles, setImageFiles] = useState([]);

  // use to check file format size and quantity
  const validateFiles = (file) => {
    // wrong format!
    if (!imgConfig.format.includes(file.type)) {
      setError("Formato de imagen no soportado!");
      return false;
    }

    // wrong image size!
    if ((imgConfig.maxSize * 1024 * 1024) < file.size) {
      setError("Tamaño de imagen superior a 2MB!");
      return false;
    }

    return true;
  };

  const handlerFileOnChange = (e) => {
    console.log("There is a file! ", e.target.files);
    // clear error
    setError("")


    // creating array files
    const images = Array.from(e.target.files);

    // validate files
    const validFiles = images.filter(validateFiles);

    // files that pass the validation
    setImageFiles(prev => [...prev, ...validFiles])


    // setting images
    handleImages(images)
  }

  const handlerDrop = (e) => {
    e.preventDefault()

    setError("")

    const files = Array.from(e.dataTransfer.files)
    console.log("images dropped: ", files)

    const validFiles = files.filter(validateFiles)

    setImageFiles(prev => [...prev, ...validFiles])

    // setting images
    handleImages(files)
  }

  return (
    <div className="flex flex-col">
      <div className="flex items-center justify-center w-full"
    onDrop={handlerDrop}
    onDragOver={(e) => e.preventDefault()}>
      <label
        htmlFor="dropzone-file"
        className={`flex flex-col items-center justify-center w-full h-auto border-2 ${error ? "border-red-300" : "border-gray-300"} border-dashed rounded-lg cursor-pointer bg-gray-50 hover:bg-gray-100`}
      >
        <div className="flex flex-col items-center justify-center pt-5 pb-6">
          <svg
            className="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 20 16"
          >
            <path
              stroke="currentColor"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"
            />
          </svg>
          <p className="mb-2 text-sm text-gray-500 dark:text-gray-400">
            <span className="font-semibold">Click to upload</span> or drag and
            drop
          </p>
          <p className={`text-xs ${error ? "text-red-500" : "text-gray-500"}`}>
            {error !== "" ? error : "PNG o JPG (MAX. 2MB)"}
          </p>
        </div>
        <input
          id="dropzone-file"
          type="file"
          accept="image/jpeg, image/jpg, image/png"
          onChange={handlerFileOnChange}
          className="hidden"
        />
      </label>
    </div>

    <div className="grid grid-cols-4 space-x-2">
    {imageFiles && imageFiles.map((img, i) => (
      <img 
      key={i}
      src={URL.createObjectURL(img)}
      alt={`image ${i}`}
      className="w-full h-full m-2"
      />
    ))}
    </div>
    </div>
  );
};

export default DragDropImage;
