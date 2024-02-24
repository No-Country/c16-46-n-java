import CardMesagge from "../cards/CardMesagge"

const Messages = ()=>{
    return (
        <>
          <h3 className="absolute w-full text-center top-3 text-gray-700 font-bold">Mensajes</h3>
          <div className="w-full p-2 flex flex-col">
            <CardMesagge />
            {/* JUST FOR TESTING --> DELETE LATER */}
            <CardMesagge />
            <CardMesagge />
          </div>
        </>
    )
}
export default Messages