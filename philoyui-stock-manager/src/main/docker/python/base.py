from datetime import datetime

from sqlalchemy import create_engine, Column, Integer, String, DateTime
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker


def init_database_engine():
    return create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')


engine = init_database_engine()
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()


class StockTag(Base):
    __tablename__ = 'stock_tag'
    id = Column(Integer, primary_key=True, autoincrement=True)
    symbol = Column(String(50))
    tag_name = Column(String(50))
    created_time = Column(DateTime(timezone=True), default=datetime.now)

    def __repr__(self):
        return "<User(name='%s', fullname='%s', nickname='%s')>" % (
                                self.name, self.fullname, self.nickname)


def delete_old_data(tag_name):
    conn = engine.connect()
    conn.execute("delete from stock_tag WHERE tag_name = '" + tag_name + "'")


def mark_stock_as_tag(stock_data, tag_name):
    # 标记股票的标签
    stock_tag = StockTag(symbol=stock_data[5], tag_name=tag_name)
    session.add(stock_tag)
    session.commit()


