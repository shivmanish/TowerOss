package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class BackhaulListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) :
    RecyclerView.Adapter<BackhaulListAdapter.ViewHold>() {

    var list: ArrayList<String> = ArrayList()

    var LINK_VIEW_TYPE = 0
    var IDU_VIEW_TYPE = 1
    var ODU_VIEW_TYPE = 2
    var ANTEENA_VIEW_TYPE = 3
    var INSTALLATION_TEAM_VIEW_TYPE = 4
    var MATERIALS_VIEW_TYPE = 5
    var LMC_VIEW_TYPE = 6
    var ATPCHECK_LIST_VIEW_TYPE = 7
    var PO_DETAILS_VIEW_TYPE = 8
    var CONNEVCTED_EQUIPMENT_VIEW_TYPE = 9

    init {
        list.add("Link")
        list.add("IDU")
        list.add("ODU")
        list.add("Antenna")
        list.add("Installation Team")
        list.add("Materials")
        list.add("LMC; Fiber")
        list.add("ATP Checklist")
        list.add("PO Details")
        list.add("Connected Equipment(CE)")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LinkViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulLinkListItemBinding = BackhaulLinkListItemBinding.bind(itemView)

        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }

    class IDUViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulIduListItemBinding = BackhaulIduListItemBinding.bind(itemView)

        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }

    class ODUViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulOduListItemBinding = BackhaulOduListItemBinding.bind(itemView)

        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }

    class AntennaViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulAntenaListItemBinding = BackhaulAntenaListItemBinding.bind(itemView)

        //  var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }

            /*   var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
               recyclerListener.adapter = adapter

               itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                   adapter.addItem()
               }*/
        }
    }

    class InstallationTeamViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulInstallationTeamListItemBinding =
            BackhaulInstallationTeamListItemBinding.bind(itemView)

        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class MaterialsViewHold(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) :
        ViewHold(itemView) {
        var binding: BackhaulConsumableMaterialsListItemBinding =
            BackhaulConsumableMaterialsListItemBinding.bind(itemView)
        var adapter = ImageAttachmentAdapter(listener)

        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.itemTitleDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.itemTitleDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    class LMCViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulLmcListItemBinding = BackhaulLmcListItemBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_up,
                    0
                )
            } else {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.down_arrow,
                    0
                )
            }


        }
    }

    class PODetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulPoDetailsListItemBinding =
            BackhaulPoDetailsListItemBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }

            /*    var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter

                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }*/
        }
    }

    class CONNEVCTEDEQUIPMENTViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulConnectedEquipmentItemListBinding =
            BackhaulConnectedEquipmentItemListBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_up,
                    0
                )
            } else {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.down_arrow,
                    0
                )
            }

            /*    var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
                recyclerListener.adapter = adapter

                itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                    adapter.addItem()
                }*/
        }
    }

    class ATPCHECKViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AtpChecklistBinding = AtpChecklistBinding.bind(itemView)

        //  var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_arrow_up,
                    0
                )
            } else {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.down_arrow,
                    0
                )
            }

            /*   var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
               recyclerListener.adapter = adapter

               itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                   adapter.addItem()
               }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item, parent, false)
        return when (viewType) {
            LINK_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_link_list_item, parent, false)
                LinkViewHold(view)
            }
            IDU_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_idu_list_item, parent, false)
                IDUViewHold(view)
            }
            ODU_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_odu_list_item, parent, false)
                ODUViewHold(view)
            }
            ANTEENA_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_antena_list_item, parent, false)
                AntennaViewHold(view)
            }
            INSTALLATION_TEAM_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_installation_team_list_item, parent, false)
                InstallationTeamViewHold(view)
            }
            MATERIALS_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_consumable_materials_list_item, parent, false)
                MaterialsViewHold(view, listener)
            }
            LMC_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_lmc_list_item, parent, false)
                LMCViewHold(view)
            }
            ATPCHECK_LIST_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.atp_checklist, parent, false)
                ATPCHECKViewHold(view)
            }
            PO_DETAILS_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_po_details_list_item, parent, false)
                PODetailsViewHold(view)
            }
            CONNEVCTED_EQUIPMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_connected_equipment_item_list, parent, false)
                CONNEVCTEDEQUIPMENTViewHold(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhaul_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Link") LINK_VIEW_TYPE
        else if (list[position] == "IDU") IDU_VIEW_TYPE
        else if (list[position] == "ODU") ODU_VIEW_TYPE
        else if (list[position] == "Antenna") ANTEENA_VIEW_TYPE
        else if (list[position] == "Installation Team") INSTALLATION_TEAM_VIEW_TYPE
        else if (list[position] == "Materials") MATERIALS_VIEW_TYPE
        else if (list[position] == "LMC; Fiber") LMC_VIEW_TYPE
        else if (list[position] == "ATP Checklist") ATPCHECK_LIST_VIEW_TYPE
        else if (list[position] == "PO Details") PO_DETAILS_VIEW_TYPE
        else if (list[position] == "Connected Equipment(CE)") CONNEVCTED_EQUIPMENT_VIEW_TYPE
        else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is LinkViewHold) {

            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfBackhaulLinkItem.visibility=
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]
        } else if (holder is IDUViewHold) {
            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfBackhaulLinkItem.visibility=
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is ODUViewHold) {
            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfBackhaulLinkItem.visibility=
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]

        } else if (holder is AntennaViewHold) {
            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfBackhaulLinkItem.visibility=
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is InstallationTeamViewHold) {
            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.lstItem.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.rfEquipmentListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.lstItem.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.editRfBackhaulLinkItem.visibility=
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is MaterialsViewHold) {
            holder.binding.itemTitleDropdown.setOnClickListener {
                holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
                if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                    holder.binding.itemTitleDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.itemTitleDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.editListItem.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
                holder.binding.addMoreListItem.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
                holder.binding.deletListItem.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE

                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]


        } else if (holder is LMCViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrow_up,
                        0
                    )
                } else {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.down_arrow,
                        0
                    )
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]

        }
        else if (holder is PODetailsViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.iconLayout.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]

        }
        else if (holder is ATPCHECKViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrow_up,
                        0
                    )
                } else {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.down_arrow,
                        0
                    )
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]

        } else if (holder is CONNEVCTEDEQUIPMENTViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_arrow_up,
                        0
                    )
                } else {
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.down_arrow,
                        0
                    )
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener {
        fun itemClicked()
    }
}