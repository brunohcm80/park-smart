package br.com.parksmart.service;

import br.com.parksmart.dto.request.FormaPagamentoRequest;
import br.com.parksmart.dto.response.FormaPagamentoResponse;
import br.com.parksmart.exception.FormaPagamentoException;
import br.com.parksmart.model.FormaPagamento;
import br.com.parksmart.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamentoResponse cadastrarFormaPagamento(FormaPagamentoRequest formaPagamentoRequest) throws FormaPagamentoException {
        FormaPagamento pagamento = formaPagamentoRequest.toFormaPagamento();

        if (pagamento.getTipoPagamentoPreferencial().equals("DEBITO") ||
                pagamento.getTipoPagamentoPreferencial().equals("CREDITO")) {

            if (pagamento.getNumeroCartao().isEmpty()) {
                throw new FormaPagamentoException("É obrigatório informar o número do cartão");
            }
            if (pagamento.getDtValidadeCartao() == null) {
                throw new FormaPagamentoException("É obrigatório informar a data de vencimento do cartao");
            }
            if (pagamento.getTitularCartao().isEmpty()) {
                throw new FormaPagamentoException("É obrigatório informar o nome do titular do cartão");
            }
        }
        var pagamentoSave = repository.save(pagamento);
        return new FormaPagamentoResponse(pagamentoSave);
    }
        public FormaPagamentoResponse obterFormaPagamentoPorCodigo(String codigoFormaPagamento) throws FormaPagamentoException{

            FormaPagamento pagamento = repository.findByCodigoFormaPagamento(codigoFormaPagamento);

            if(pagamento.equals(null)) {
                new FormaPagamentoException("Forma pagamento não encontrada");
            }
            return new FormaPagamentoResponse().toFormaPagamentoResponse(pagamento);
        }

        public Page<FormaPagamentoResponse> obterTodos(Pageable paginacao){

            var pagamento = repository.findAll();
            var pagamentoResponse = pagamento.stream().map(pagamentos
                    -> new FormaPagamentoResponse().toFormaPagamentoResponse(pagamentos)).toList();

            return new PageImpl<>(pagamentoResponse, paginacao, pagamentoResponse.size());
        }

    }

