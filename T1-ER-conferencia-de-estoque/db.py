from config import app
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy(app)


class Estoque(db.Model):
    __tablename_ = 'produtos'
    id = db.Column(db.Integer, primary_key=True)
    setor =db.Column(db.Text)
    nome = db.Column(db.Text, unique=True)
    marca_produto =db.Column(db.Text)
    validade = db.Column(db.Text)
    fornecedor = db.Column(db.Text)
    valor_compra = db.Column(db.Text)
    valor_venda = db.Column(db.Text)
    quantidade_estoque = db.Column(db.Text)
    quantidade_minima = db.Column(db.Text)

db.create_all()
db.session.commit()