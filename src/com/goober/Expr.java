package com.goober;

import java.util.List;

abstract class Expr {
	interface Visitor<T> {
		T visitAssignmentExpr(Assignment expr);
		T visitBinaryExpr(Binary expr);
		T visitCallExpr(Call expr);
		T visitGetExpr(Get expr);
		T visitSetExpr(Set expr);
		T visitThisExpr(This expr);
		T visitGroupingExpr(Grouping expr);
		T visitLiteralExpr(Literal expr);
		T visitLogicalExpr(Logical expr);
		T visitUnaryExpr(Unary expr);
		T visitVariableExpr(Variable expr);
		T visitSuperExpr(Super expr);
	}

	abstract <T> T accept(Visitor<T> visitor);

	static class Assignment extends Expr {
		final Token name;
		final Expr value;

		Assignment(Token name, Expr value) {
			this.name = name;
			this.value = value;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitAssignmentExpr(this);
		}
	}

	static class Binary extends Expr {
		final Expr left;
		final Token operator;
		final Expr right;

		Binary(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitBinaryExpr(this);
		}
	}

	static class Call extends Expr {
		final Expr callee;
		final Token paren;
		final List<Expr> arguments;

		Call(Expr callee, Token paren, List<Expr> arguments) {
			this.callee = callee;
			this.paren = paren;
			this.arguments = arguments;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitCallExpr(this);
		}
	}

	static class Get extends Expr {
		final Expr object;
		final Token name;

		Get(Expr object, Token name) {
			this.object = object;
			this.name = name;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitGetExpr(this);
		}
	}

	static class Set extends Expr {
		final Expr object;
		final Token name;
		final Expr value;

		Set(Expr object, Token name, Expr value) {
			this.object = object;
			this.name = name;
			this.value = value;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitSetExpr(this);
		}
	}

	static class This extends Expr {
		final Token keyword;

		This(Token keyword) {
			this.keyword = keyword;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitThisExpr(this);
		}
	}

	static class Grouping extends Expr {
		final Expr expression;

		Grouping(Expr expression) {
			this.expression = expression;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitGroupingExpr(this);
		}
	}

	static class Literal extends Expr {
		final Object value;

		Literal(Object value) {
			this.value = value;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitLiteralExpr(this);
		}
	}

	static class Logical extends Expr {
		final Expr left;
		final Token operator;
		final Expr right;

		Logical(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitLogicalExpr(this);
		}
	}

	static class Unary extends Expr {
		final Token operator;
		final Expr right;

		Unary(Token operator, Expr right) {
			this.operator = operator;
			this.right = right;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitUnaryExpr(this);
		}
	}

	static class Variable extends Expr {
		final Token name;

		Variable(Token name) {
			this.name = name;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitVariableExpr(this);
		}
	}

	static class Super extends Expr {
		final Token keyword;
		final Token method;

		Super(Token keyword, Token method) {
			this.keyword = keyword;
			this.method = method;
		}

		@Override
		<T> T accept(Visitor<T> visitor) {
			return visitor.visitSuperExpr(this);
		}
	}

}
