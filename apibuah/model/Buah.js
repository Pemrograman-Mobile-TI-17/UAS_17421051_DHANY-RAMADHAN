const mongoose = require('mongoose');

const userSchema = mongoose.Schema({

    kodeBuah: {
        type: String
    },
    namaBuah: {
        type: String
    },
    asalbuah: {
        type: String
    },
    namadistributor: {
        type: String
    },

    hargaBuah: {
        type: String
    },
    gambar: {
        type: String
    }
})
module.exports = mongoose.model('buah', userSchema)