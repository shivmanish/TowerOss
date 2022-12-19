package com.smarthub.baseapplication.model.notification

data class NotificationListItem(var subTitle : String = "Ajay",
                                var frequenTime : String = "5 min ago",
                                var time : String = "10:00 am",
                                var Date : String = "13-Dec-22",
                                var Action: Boolean = false,
                                var sublist: ArrayList<NotificationData>
                                )
