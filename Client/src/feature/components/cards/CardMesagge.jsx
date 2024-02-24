import React from 'react'

const CardMesagge = () => {
  return (
    <div className='flex items-center w-full border-b-2 border-gray-400 p-2'>
      
      {/* PROFILE IMAGE */}
      <img 
      className='w-10 h-10 bg-contain rounded-full'
      src='https://www.wilsoncenter.org/sites/default/files/media/images/person/james-person-1.jpg'/>
      
      <div className="w-full h-full ml-4 flex flex-col">
        <span className='w-full text-end text-[10px]'>10/09/2020</span>
        <h5 className="font-bold text-md w-full">Nombre de usuario</h5>
        <p className="w-full text-slate-700">Último mensaje del chat</p>
      </div>

    </div>
  )
}

export default CardMesagge
