import CardNotification from "../cards/CardNotification"

const Notifications = ()=>{
    return (
        <>
          <h3 className="absolute w-full text-center top-3 text-gray-700 font-bold">Notificaciones</h3>
          <div className="w-full flex flex-col space-y-2">
            <CardNotification />
            {/* JUST FOR TESTING --> DELETE LATER */}
            <CardNotification />
            <CardNotification />
          </div>
        </>
    )
}
export default Notifications