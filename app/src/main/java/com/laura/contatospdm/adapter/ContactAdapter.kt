package com.laura.contatospdm.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.getSystemService
import com.laura.contatospdm.R
import com.laura.contatospdm.databinding.TileContactBinding
import com.laura.contatospdm.model.Contact

// construtor da subclasse: ContactAdapter() <-

class ContactAdapter(
        context: Context,
        private val contactList: MutableList<Contact>
    ): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {

    // função de callback - quando precisa de uma nova célula, o ListView chama o Adapter,
    // invocando o método getView e passando esses parâmetros: position, convertView e parent

    // convertView: ele pode ser um objeto do tipo view ou pode ser nulo.
    // se for nulo, é que é necessário criar uma nova célula e não existem células a serem
    // recicladas. se não for, é o que queremos, pois será enviada uma célula que não
    // está na tela ainda e pode ter seus parâmetros trocados.
    // o limite de dados na tela é dado por um manager específico.

    // getview tem duas ações: no datasource, pega o contato que vai servir de base e preenche a célula.
    // primeiramente, ele infla a célula, mas basicamente são essas duas ações.

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList[position]
        var contactTileView = convertView
        var tcb: TileContactBinding?= null

        if(contactTileView == null) {
            tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root
            val tileContactHolder = TileContactHolder(tcb.nameTv, tcb.emailTv)
            contactTileView.tag = tileContactHolder
        }

        // as -> forma de fazer cast
        val holder = contactTileView.tag as TileContactHolder
        holder.nameTv.setText(contact.name)
        holder.emailTv.setText(contact.email)


        return contactTileView
    }

    private data class TileContactHolder(val nameTv: TextView, val emailTv: TextView)
}