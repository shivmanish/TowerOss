package com.smarthub.baseapplication.model.siteInfo.utilitiesEquip

data class BatteryBank(
    val AcceptanceStatus: String,
    val CabinetSizeB: String,
    val CabinetSizeH: String,
    val CabinetSizeL: String,
    val ConditionalAcceptanceDate: String,
    val ConsumableMaterials: List<BatteryConsumableMaterial>,
    val EquipmentName: String,
    val FinalAcceptanceDate: String,
    val InstallationDate: String,
    val InstallationExecutiveName: String,
    val InstallationExecutiveNumber: String,
    val InstallationVendor: String,
    val InstalledLocationType: String,
    val LocationMark: String,
    val MaintenanceVendor: String,
    val Make: String,
    val ManufacturingMonthYear: String,
    val MaxPowerRating: String,
    val Model: String,
    val NextPMDate: String,
    val OPVolatgeReading: String,
    val OperatingTempMax: String,
    val OperatingTempMin: String,
    val OperationStatus: String,
    val OverallWeight: String,
    val OwnerCompany: String,
    val PMInterval: String,
    val RackID: String,
    val RectifierModule: List<BatteryRectifierModule>,
    val SerialNumber: String,
    val ServiceDetails: String,
    val UserCompany: String,
    val WarrantyExpiryDate: String,
    val WarrantyPeriod: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)