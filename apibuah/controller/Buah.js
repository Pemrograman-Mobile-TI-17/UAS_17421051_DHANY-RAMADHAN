const buah = require('../model/Buah.js')
const response = require('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId
exports.inputDataBuah = (data, gambar) =>
    new Promise(async (resolve, reject) =>{

        const buahBaru = new buah({
            kodeBuah : data.kodeBuah,
            namaBuah : data.namaBuah,
            asalbuah: data.asalbuah,
            namadistributor: data.namadistributor,
            hargaBuah: data.hargaBuah,
            gambar: gambar
        })

        await buah.findOne({kodeBuah: data.kodeBuah})
            .then(buah =>{
                if (buah){
                    reject(response.commonErrorMsg('Kode Buah Sudah ada'))
                }else{
                    buahBaru.save()
                        .then(r =>{
                            resolve(response.commonSuccessMsg('Berhasil Menginput Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf Input Buah Gagal'))
                    })
                }
            }).catch(err =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan, mohon tunggu sebentar'))
            })
    })

exports.lihatDataBuah = () =>
    new Promise(async (resolve, reject) =>{
        await buah.find({})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan, mohon tunggu sebentar')))
    })

exports.lihatDetailDataBuah = (kodeBuah) =>
    new Promise(async (resolve, reject) =>{
        await buah.findOne({kodeBuah: kodeBuah})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan, mohon tunggu sebentar')))
    })

exports.updateBuah = (id, data, gambar) =>
    new Promise(async (resolve, reject)=>{
        await buah.updateOne(
            {_id : ObjectId(id)},
            {
                $set: {
                    kodeBuah : data.kodeBuah,
                    namaBuah : data.namaBuah,
                    asalbuah: data.asalbuah,
                    namadistributor: data.namadistributor,
                    hargaBuah: data.hargaBuah,
                    gambar: gambar
                }
            }
        ).then(buah =>{
            resolve(response.commonSuccessMsg('Berhasil Mengubah Data'))
        }).catch(err =>{
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan, mohon tunggu sebentar'))
        })
    })

exports.hapusbuah = (_id) =>
    new Promise(async (resolve, reject) =>{
        await buah.remove({_id: ObjectId(_id)})
            .then(() =>{
                resolve(response.commonSuccessMsg('Berhasil Menghapus Data'))
            }).catch(() =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan, mohon tunggu sebentar'))
            })
    })